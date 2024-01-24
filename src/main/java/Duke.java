import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


        System.out.println("        ------------------------------------------------------------");
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println("        ------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("        ------------------------------------------------------------");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("        ------------------------------------------------------------");
                break;
            } else {
                System.out.println("        ------------------------------------------------------------");
                System.out.println("        " + command);
                System.out.println("        ------------------------------------------------------------");
            }
        }
    }
}
