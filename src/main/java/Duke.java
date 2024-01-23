import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasksList = new ArrayList<Task>();
        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("User:");
            String input = scanner.nextLine();

            if (input.contains("bye")) {
                break;
            }

            String command = input.split(" ")[0];

            if (command.equals("todo")) {
                String details = input.split(" ", 2)[1];
                Task task = new Todo(details);
                tasksList.add(task);
                System.out.println("DevGPT:\n\t" + " Got it. I've added this task: \n\t" + task.toString());
                System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
            } else if (command.equals("deadline")) {
                String details = input.split(" ", 2)[1];
                String description = details.split("/by ")[0].trim();
                String by = details.split("/by ")[1];
                Task task = new Deadline(description, by);
                tasksList.add(task);
                System.out.println("DevGPT:\n\t" + " Got it. I've added this task: " + task.toString());
                System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
            } else if (command.equals("event")) {

                String details = input.split(" ", 2)[1];
                String description = details.split("/from")[0].trim();;
                String from =  details.split("/from")[1].split("/to")[0];
                String to = details.split("/to")[1];
                Task task = new Event(description, from, to);
                tasksList.add(task);
                System.out.println("DevGPT:\n\t" + " Got it. I've added this task: " + task.toString());
                System.out.println("DevGPT:\n\t Now you have " + tasksList.size() + " tasks in the list.");
            } else if (command.equals("list")) {
                System.out.println("DevGPT:\n\t Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    Task task = tasksList.get(i);
                    System.out.println("\t\t" + (i + 1) + ". " + task.toString());
                }
            } else if (command.equals("unmark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                Task task = tasksList.get(index - 1);
                task.markAsUndone();
                System.out.println("DevGPT:\n\t Got it! I've marked this task as not done yet:");
                System.out.println("\t" + task.toString());
            } else if (input.equals("mark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                Task task = tasksList.get(index - 1);
                task.markAsDone();
                System.out.println("DevGPT:\n\t Nice! I've marked this task as done:");
                System.out.println("\t" + task.toString());
            } else {
                tasksList.add(new Task(input));
                System.out.println("DevGPT:\n\t" + "added: " + input);
            }

        }
        scanner.close();
        System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");

    }
}
