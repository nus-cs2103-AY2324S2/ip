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
                    System.out.println((i + 1) + ". " + currTask);
                }
            } else if (input.startsWith("mark")) {
                Integer indexOfTask = Integer.parseInt(input.substring(5));
                Task currTask = tasks.get(indexOfTask);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currTask);
            } else if (input.startsWith("unmark")) {
                Integer indexOfTask = Integer.parseInt(input.substring(7));
                Task currTask = tasks.get(indexOfTask);
                currTask.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currTask);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                Task todo = new Todo(description);
                tasks.add(todo);
                System.out.println("Got it. I've added this task: \n" + todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String[] substrings = input.split(" /by ");
                String description = substrings[0].substring(9);
                String by = substrings[1];
                Task deadline = new Deadline(description, by);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task: \n" + deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String[] substrings = input.split(" /from ");
                String description = substrings[0].substring(6);
                String[] substrings2 = substrings[1].split(" /to ");
                String from = substrings2[0];
                String to = substrings2[1];
                Task event = new Event(description, from, to);
                tasks.add(event);
                System.out.println("Got it. I've added this task: \n" + event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {

            }
        }

        // Close the scanner
        sc.close();
    }
}
