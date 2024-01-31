import java.util.*;

public class Linus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Linus!\nWhat can I do for you?\n\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // while loop to repeat printing of multiple Scanner inputs
        // adapted with help of AI
        while (true) {
            String input = sc.nextLine();

            // Check if the user wants to exit.
            // When comparing strings for equality, you should use the equals() method, not the == operator.
            if (input.equals("bye")) { // exit chat
                System.out.println("Bye. It's been a pleasure chatting with you!");
                break;
            } else if (input.equals("list")) { // list tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    System.out.println((i + 1) + ". [" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                }
            } else if (input.startsWith("mark")) {
                Integer indexOfTask = Integer.parseInt(input.substring(5));
                Task currTask = tasks.get(indexOfTask);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            } else if (input.startsWith("unmark")) {
                Integer indexOfTask = Integer.parseInt(input.substring(7));
                Task currTask = tasks.get(indexOfTask);
                currTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            } else { // add tasks
                Task addedTask = new Task(input);
                System.out.println("added: " + addedTask.getDescription()); // println automatically inserts new line
                tasks.add(addedTask);
            }
        }

        // Close the scanner
        sc.close();
    }
}
