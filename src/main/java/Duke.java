import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> storage;
    static ArrayList<String> instructions = new ArrayList<String>();
    static int count = 0;
    private static Data data;
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        storage = new ArrayList<Task>();
        data = new Data("./data/duke.txt");
        data.createFolder();
        data.createFile();
        data.loadTasks();
        // instruction for the chatbot to follow
        String instruction;
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n");

        boolean chatting = true;
        while (chatting) {
            if (sc.hasNextLine()) {
                // checks if there is another instruction
                instruction = sc.nextLine();
            } else {
                // ends conversation
                sc.close();
                chatting = false;
                break;
            }
            if (instruction.equals("bye")) {
                data.setTasks();
                data.saveTasks();
                // command to end chat with chatbot
                System.out.println("Bye. Hope to see you again soon!");
                chatting = false;
                break;
            } else if (instruction.equals("list")) {
                // lists out all tasks
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + "." + storage.get(i).toString());
                }
                System.out.println();
            } else {
                String[] sentence = instruction.split(" ", 2);
                if (sentence.length == 1) {
                    DukeException error = new DukeException(instruction);
                    System.out.println(error.toString());
                } else {
                    instructions.add(instruction);
                    String command = sentence[0];
                    if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                        addTask(instruction);
                    } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                        modifyTask(instruction);
                    } else {
                        // any instruction that is not clear that has not been covered
                        DukeException error = new DukeException(instruction);
                        System.out.println(error.toString());
                    }
                }
            }
        }

    }

    public static void addTask(String instruction) {
        if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")) {
            DukeException error = new DukeException((instruction));
            System.out.println(error.toString());
        } else {
            String[] sentence = instruction.split(" ", 2);
            String command = sentence[0];
            String description = sentence[1];
            if (command.equals("todo")) {
                storage.add(new Todo(description));
                count++; // keep track of number of tasks
                System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                        + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
            } else if (command.equals("deadline")) {
                String[] arr = description.split(" /by "); // splits the task into description and deadline
                storage.add(new Deadline(arr[0], arr[1]));
                count++; // keep track of number of tasks
                System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                        + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
            } else if (command.equals("event")) {
                String[] arr = description.split(" /from "); // split task into description and deadline
                String[] arr1 = arr[1].split(" /to "); // split deadline into from and to
                storage.add(new Event(arr[0], arr1[0], arr1[1]));
                count++; // keeps track of tasks
                System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                        + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
            }
        }
    }

    public static void modifyTask(String instruction) {
        String[] arr = instruction.split(" ");  // splits the task into individual word
        if(arr.length == 1){
            DukeException error = new DukeException((instruction));
            System.out.println(error.toString());
        } else {
            if (instruction.contains("mark")) {
                // marks a task as done
                int num = Integer.parseInt(arr[1]) - 1; // task to be unmarked
                storage.get(num).taskDone(); // marks a task as done
                System.out.println("Nice! I've marked this task as done:\n " +
                        storage.get(num).toString());
            } else if (instruction.contains("unmark")) {
                // marks a specific task as undone
                int num = Integer.parseInt(arr[1]) - 1; // task to be unmarked
                storage.get(num).taskUndone(); // marks a task as done

                System.out.println("OK, I've marked this task as not done yet:\n " +
                        storage.get(num).toString());
            } else if (instruction.contains("delete")) {
                Task removal = storage.get(Integer.parseInt(arr[1]) - 1); // task to be removed
                storage.remove(Integer.parseInt(arr[1]) - 1);
                count -= 1;
                System.out.println("Noted. I've removed this task:\n" + removal.toString() + "\n" + "Now you have "
                        + count + " tasks in the list.\n");
            }
        }
    }
}
