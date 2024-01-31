import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n"
        );

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<Task>();
        int count = 0;
        String instruction; // instruction for the chatbot to follow
        while(true){
            if(sc.hasNextLine()) {
                // checks if there is another instruction
                instruction = sc.nextLine();
            }else{
                // ends conversation
                sc.close();
                break;
            }
            if(instruction.equals("bye")){ // command to end chat with chatbot
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(instruction.equals("list")){
                // lists out all tasks
                for(int i=0;i<count;i++){
                        System.out.println(i + 1 + "." + storage.get(i).toString());
                }
                System.out.println();
            }else if(instruction.contains("unmark")){
                // marks a specific task as undone
                String[] arr = instruction.split(" ");
                int num = Integer.parseInt(arr[1]) - 1; // task to be unmarked
                storage.get(num).taskUndone(); //marks a task as done
                System.out.println("OK, I've marked this task as not done yet:\n " +
                        storage.get(num).toString());
            }else if(instruction.contains("mark")){
                // marks a task as done
                String[] arr = instruction.split(" "); // splits the task into individual word
                int num = Integer.parseInt(arr[1]) - 1; // task to be unmarked
                storage.get(num).taskDone(); // marks a task as done
                System.out.println("Nice! I've marked this task as done:\n " +
                        storage.get(num).toString());
            }else if(instruction.contains("deadline")){
                if(instruction.equals("deadline")){
                    // throws error for incomplete instruction
                    DukeException error = new DukeException(instruction);
                    System.out.println(error.toString());
                }else {
                    String[] arr = instruction.split(" /by "); // splits the task into description and deadline
                    storage.add(new Deadline(arr[0].substring(9), arr[1]));
                    count++; // keep track of number of tasks
                    System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                            + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
                }
            }else if(instruction.contains("todo")){
                if(instruction.equals("todo")){
                    // throws error for incomplete instruction
                    DukeException error = new DukeException(instruction);
                    System.out.println(error.toString());
                }else {
                    storage.add(new Todo(instruction.substring(5)));
                    count++; //keep track of number of tasks
                    System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                            + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
                }
            }else if(instruction.contains("event")){
                if(instruction.equals("event")){
                    // throw error for incomplete instruction
                    DukeException error = new DukeException(instruction);
                    System.out.println(error.toString());
                }else {
                    String[] arr = instruction.split(" /from "); // split task into description and deadline
                    String[] arr1 = arr[1].split(" /to "); // split deadline into from and to
                    storage.add(new Event(arr[0].substring(6), arr1[0], arr1[1]));
                    count++; // keeps track of tasks
                    System.out.println("Got it. I've added this task:\n" + storage.get(count - 1).toString()
                            + "\n" + "Now you have " + count + " tasks in the list" + ".\n");
                }
            }else if(instruction.contains("delete")){
                String[] arr = instruction.split(" "); // split instruction into delete and index of item to be deleted
                Task removal = storage.get(Integer.parseInt(arr[1]) - 1); // task to be removed
                storage.remove(Integer.parseInt(arr[1]) - 1);
                count -=1;
                System.out.println("Noted. I've removed this task:\n" + removal.toString() + "\n" + "Now you have "
                        + count + " tasks in the list.\n");

            }else{
                /*
                any instruction that is not clear that has not
                been covered
                */
                DukeException error = new DukeException(instruction);
                System.out.println(error.toString());
            }
        }
    }

}
