import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Charlie {
    public static void main(String[] args) {

        List<Task> taskList = new ArrayList<>();

        System.out.println("Hello, I'm Charlie");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                taskList.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index));
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                taskList.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + taskList.get(index));
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else {
                taskList.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}
