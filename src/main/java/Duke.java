import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final Path TASKS_CACHE_PATH = Path.of(".duke-cache");
    public static List<Task> tasksList = new ArrayList<>();
    private static final String horiLine = "---------------------------------\n";

    enum Instruction {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
        }
    private static Instruction toInstruction(String input) throws DukeException {
        try {
            return Instruction.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Please enter instruction in the correct format" +
                    "\nHere are valid instructions: list, mark, unmark, deadline, event, todo");
        }
    }

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + horiLine;
        System.out.println(greet);
    }

    public static void main(String[] args) {

        load();
        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(horiLine);
            try {
                processInput(input);
            } catch(DukeException e) {
                System.out.println("Invalid Instruction: " + e.getMessage());
            }
            System.out.println(horiLine);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void processInput(String input) throws DukeException {
        String[] parsedInput = input.split(" ", 2);
        Instruction ins = toInstruction(parsedInput[0]);
        String details = parsedInput.length > 1 ? parsedInput[1] : "";
        switch (ins) {
            case LIST:
                listTasks();
                save();
                break;
            case MARK:
                completeTask(details);
                save();
                break;
            case UNMARK:
                uncompleteTask(details);
                save();
                break;
            case TODO:
                addTodo(details);
                save();
                break;
            case DEADLINE:
                addDeadline(details);
                save();
                break;
            case EVENT:
                addEvent(details);
                save();
                break;
            case DELETE:
                deleteTask(details);
                save();
                break;
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasksList.get(i));
        }
    }


    private static void completeTask(String index) throws DukeException{
        if (index.length() < 1) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. unmark 2"); }
        try {
            int i = Integer.parseInt(index) - 1;
            tasksList.get(i).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksList.get(i).toString());
        } catch (Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

    private static void uncompleteTask(String index) throws DukeException{
        if(index.length() < 1) { throw new DukeException("Please enter the task number that you want to mark as incomplete: ex. mark 2"); }
        try {
            int i = Integer.parseInt(index) - 1;
            tasksList.get(i).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(tasksList.get(i).toString());
        } catch(Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

    private static void addTodo(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Please enter task description");
        }
        tasksList.add(new Todo(details));
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + tasksList.get(tasksList.size()-1).toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void addDeadline(String details) throws DukeException{
        String[] parsedInput = details.split("/", 2);
        if (parsedInput.length != 2) {
            throw new DukeException("Please enter task description and deadline \n correct format: deadline *task description* /by *deadline*");}
        tasksList.add(new Deadline(parsedInput[0], parsedInput[1].substring(3)));
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + tasksList.get(tasksList.size()-1).toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void addEvent(String details) throws DukeException{
        try {
            String[] parsedInput = details.split("/", 3);
            tasksList.add(new Event(parsedInput[0], parsedInput[1].substring(5), parsedInput[2].substring(3)));
            System.out.println("Got it. I've added this task:");
            System.out.println("added: " + tasksList.get(tasksList.size()-1).toString());
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        } catch (Exception e){
            throw new DukeException("Please enter event description and time in the correct format\ncorrect format: event *event name* /from *date-time* /to *date-time*");
        }
    }

    private static void deleteTask(String index) throws DukeException{
        if (index.length() < 1) { throw new DukeException("Please enter the task number that you want to delete: ex. delete 2"); }
        try {
            int i = Integer.parseInt(index) - 1;
            String task = tasksList.get(i).toString();
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            tasksList.remove(i);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        } catch(Exception e) {
            throw new DukeException("Please enter the valid task number");
        }
    }

    private static void load() {
        if (Files.notExists(TASKS_CACHE_PATH)) {
            System.out.println("No cache found");
            tasksList = new ArrayList<>();
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(TASKS_CACHE_PATH.toString());
                ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
                tasksList = (List<Task>) objInputStream.readObject();

                objInputStream.close();
                fileInputStream.close();
                System.out.println(String.format("Tasks downloaded from %s", TASKS_CACHE_PATH));
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Unable to download existing tasks");
                tasksList = new ArrayList<>();
                try {
                    Files.delete(TASKS_CACHE_PATH);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(TASKS_CACHE_PATH.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(tasksList);
            
            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }

}
