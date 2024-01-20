import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String message = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bob!");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
        while (true) {
           message = scanner.nextLine();
            if (message.equals("bye")) {
                break;
            } else if (message.startsWith("add ")) {
                String description = message.substring(4);
                Task task = new Task(description);
                list.add(task);
                System.out.println("------------------------------------------");
                System.out.println("added: " + task.getDescription());
                System.out.println("------------------------------------------");
            } else if (Objects.equals(message, "list")) {
                System.out.println("------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println((i + 1) + ". " + (task.toString()));
                }
                System.out.println("------------------------------------------");
            } else if (message.startsWith("mark ")) {
                int index = Integer.parseInt(message.substring(5)) - 1;
                Task task = list.get(index);
                task.markAsDone();
                System.out.println("------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getDescription());
                System.out.println("------------------------------------------");
            } else if (message.startsWith("unmark ")) {
                int index = Integer.parseInt(message.substring(7)) - 1;
                Task task = list.get(index);
                task.unMarkAsDone();
                System.out.println("------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getDescription());
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }
}
