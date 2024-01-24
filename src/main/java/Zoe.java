import java.util.Scanner;
import java.util.*;
public class Zoe {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println("Hello! I'm Zoe");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format
                            ("%d.%s", i + 1, tasks.get(i).getStatus()));
                }
            } else {
                //at this stage there is only the mark and unmark command
                String[] markUnmark = command.split(" ");
                if (markUnmark[0].equals("mark")) {
                    int idx = Integer.parseInt(markUnmark[1]) - 1;
                    tasks.get(idx).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(idx).getStatus());
                } else if (markUnmark[0].equals("unmark")){
                    int idx = Integer.parseInt(markUnmark[1]) - 1;
                    tasks.get(idx).unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(tasks.get(idx).getStatus());
                } else {
                    tasks.add(new Task(command));
                    System.out.println("added: " + command);
                }
            }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
