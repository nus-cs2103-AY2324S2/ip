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

        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tHello! I'm Duchess");
        System.out.println("\t\tWhat can I do for you?");
        System.out.println("\t\t------------------------------------------");

        Scanner scan = new Scanner( System.in );
        String input = scan.nextLine();
        ArrayList<String> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("\t\t------------------------------------------");
                System.out.println("\t\t" + list.size() + " item(s) in the list");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("\t\t" + i + ". " + list.get(i - 1));
                }
                System.out.println("\t\t------------------------------------------");
            }
            else {
                System.out.println("\t\t------------------------------------------");
                System.out.println("\t\tAdded: " + input);
                list.add(input);
                System.out.println("\t\t------------------------------------------");
            }
            input = scan.nextLine();
        }

        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println("\t\t------------------------------------------");
    }
}
