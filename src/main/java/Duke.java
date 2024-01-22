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
            } else if (input.contains("list")) {
                System.out.println("DevGPT:\n\t Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    Task task = tasksList.get(i);
                    System.out.println("\t" + (i + 1) + ". " + "[" + task.getStatusIcon() + "]" + " " + task.description);
                }

            } else if (input.contains("unmark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                Task task = tasksList.get(index - 1);
                task.markAsUndone();
                System.out.println("DevGPT:\n\t Got it! I've marked this task as not done yet:");
                System.out.println("\t" + "[" + task.getStatusIcon() + "]" + " " + task.description);
            } else if (input.contains("mark")) {
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                Task task = tasksList.get(index - 1);
                task.markAsDone();
                System.out.println("DevGPT:\n\t Nice! I've marked this task as done:");
                System.out.println("\t" + "[" + task.getStatusIcon() + "]" + " " + task.description);
            } else {
                tasksList.add(new Task(input));
                System.out.println("DevGPT:\n\t" + "added: " + input);
            }

        }
        scanner.close();
        System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");

    }
}
