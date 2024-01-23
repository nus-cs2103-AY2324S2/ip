import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        String lineBreak = "\t\t------------------------------------------";

        System.out.println(lineBreak);
        System.out.println("\t\tHello! I'm Duchess");
        System.out.println("\t\tWhat can I do for you?");
        System.out.println(lineBreak);

        Scanner scan = new Scanner( System.in );
        String input = scan.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(lineBreak);
                System.out.println("\t\t" + list.size() + " item(s) in the list");
                for (int i = 1; i <= list.size(); i++) {
                    Task t = list.get(i - 1);
                    System.out.println("\t\t" + i + ".[" + t.getStatusIcon() + "] " + t.getDescription());
                }
                System.out.println(lineBreak);
            }
            else if (input.contains("unmark")) {
                System.out.println(input.charAt(7));
                int item_index = Character.getNumericValue(input.charAt(7));
                Task t = list.get(item_index-1);
                t.markAsUndone();
                System.out.println(lineBreak);
                System.out.println("\t\tOK, I've marked this task as not done yet:");
                System.out.println("\t\t  [" + t.getStatusIcon() + "] " + t.getDescription());
                System.out.println(lineBreak);
            }
            else if (input.contains("mark")) {
                int item_index = Character.getNumericValue(input.charAt(5));
                Task t = list.get(item_index-1);
                t.markAsDone();
                System.out.println(lineBreak);
                System.out.println("\t\tNice! I've marked this task as done:");
                System.out.println("\t\t  [" + t.getStatusIcon() + "] " + t.getDescription());
                System.out.println(lineBreak);
            }
            else {
                System.out.println(lineBreak);
                Task t = new Task(input);
                list.add(t);
                System.out.println("\t\tAdded: " + input);
                System.out.println(lineBreak);
            }
            input = scan.nextLine();
        }

        System.out.println(lineBreak);
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}