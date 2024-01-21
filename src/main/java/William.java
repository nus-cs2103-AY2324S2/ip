import java.util.*;

public class William {
    public static void main(String[] args) {
        /*
         * String logo = " ____        _        \n"
         * + "|  _ \\ _   _| | _____ \n"
         * + "| | | | | | | |/ / _ \\\n"
         * + "| |_| | |_| |   <  __/\n"
         * + "|____/ \\__,_|_|\\_\\___|\n";
         * System.out.println("Hello from\n" + logo);
         */

        Scanner sc = new Scanner(System.in);
        String logo = "William";

        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            Commands command = Commands.valueOf(input);

            switch (command) {
                case list:
                    System.out.println("list\n");
                    break;
                case blah:
                    System.out.println("blah\n");
                    break;
                case bye:
                    System.out.println("Bye. Hope to see you again soon!");
                    sc.close();
                    return;
                default:
                    System.out.println("Unknown command, please try again!");
            }
        }
    }
}
