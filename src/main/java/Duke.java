import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?" );
        System.out.println("-------------------------------");

        while (true) {
            String text1 = scan.nextLine();
            int indentation = text1.length() + 1;
            String spaces = " ".repeat(indentation);

            if (text1.equals("bye")) {
                System.out.println("-------------------------------");
                System.out.println(spaces + "Bye. Hope to see you again soon!");
                System.out.println("-------------------------------");
                break;
            }

            System.out.println("-------------------------------");
            System.out.println(spaces + text1);
            System.out.println("-------------------------------");
        }
    }
}
