import java.util.*;
public class Lia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for(int j = 1; j <= tasks.size(); j++) {
                    Task task = tasks.get(j - 1);
                    System.out.println(j + ". [" + task.getStatusIcon() + "] " + task.description);
                }
            } else if (input.startsWith("mark")) {
               String[] tokens = input.split(" ");
               int pos = Integer.parseInt(tokens[1]);
               Task task = tasks.get(pos - 1);
               task.markAsDone();
               System.out.println("Nice! I've marked this task as done:");
               System.out.println("[" + task.getStatusIcon() + "] " + task.description);
            } else if (input.startsWith("unmark")) {
                String[] tokens = input.split(" ");
                int pos = Integer.parseInt(tokens[1]);
                Task task = tasks.get(pos - 1);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.description);
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
