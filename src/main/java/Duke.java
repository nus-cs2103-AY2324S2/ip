import java.util.Scanner;
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
        while (!input.equals("bye")) {
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\tLet me copy you!\n\t\tYou typed:\n\t\t" + input);
            System.out.println("\t\t------------------------------------------");
            input = scan.nextLine();
        }

        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tBye. Hope to see you again soon!");
        System.out.println("\t\t------------------------------------------");
    }
}
