import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "James";
        System.out.println("Hello! I'm " + name + "\n");
        System.out.println("What can I do for you? \n");

        while(true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon! \n");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (command.equalsIgnoreCase("blah")) {
                System.out.println("blah");
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task task = tasks.get(index);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + task);
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task task = tasks.get(index);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + task);
            } else {
                tasks.add(new Task(input));
            }
        }
    }
}
