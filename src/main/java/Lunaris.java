import java.util.ArrayList;
import java.util.*;
public class Lunaris {
    public static void main(String[] args) {
        String name = "Lunaris";
        String indentation = "  ";
        String indentedLine = "  _________________________________________________________";
        // Just for convenience of copy paste.
        // System.out.println(indentedLine);

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
            + indentation + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        ArrayList<Task> inputList = new ArrayList<>();

        /*
        Main body of addList task.
        Handle case where input is "bye" or "list".
        Add input to arrayList otherwise.
        */
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            int taskId = 0;
            if (input.contains("mark") || input.contains("unmark")) {
                taskId = Integer.parseInt(input.split(" ")[1]) - 1; // subtract 1 for array index.
            }
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(indentedLine);
                System.out.println(indentation +
                        "Leaving so soon? Alright, have a great day ahead!");
                System.out.println(indentedLine);
                break;
            }
            // Display the current list of tasks
            if (input.equalsIgnoreCase("list")) {
                System.out.println(indentedLine);
                System.out.println(indentation + "Here are the tasks in your list:");
                for (int i = 0; i < inputList.size(); i++){
                    Task currTask = inputList.get(i);
                    System.out.println(indentation + (i + 1) + currTask.toString());
                }
                System.out.println(indentedLine);
            }
            // For Marking selected task
            if (input.startsWith("mark")) {
                inputList.get(taskId).markDone();
                System.out.println(indentedLine);
                System.out.println(indentation + "Nice! I've marked this task as done:");
                System.out.println(inputList.get(taskId).toString());
                System.out.println(indentedLine);
            }
            // For Unmarking selected task
            if (input.startsWith("unmark")) {
                inputList.get(taskId).markNotDone();
                System.out.println(indentedLine);
                System.out.println(indentation + "Ok, I've marked this task as not done yet:");
                System.out.println(inputList.get(taskId).toString());
                System.out.println(indentedLine);
            }
            if (!input.contains("mark") && !input.contains("unmark") &&
                    !input.contains("bye") && !input.contains("list")) {
                Task newTask = new Task(input);
                inputList.add(newTask);
                System.out.println(indentedLine);
                System.out.println(indentation + "added: " + input);
                System.out.println(indentedLine);
            }
        }
    }
}
