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
        ArrayList<String> tasks = new ArrayList<String>();
        int noOfTasks = 0;

        while (true) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("        ------------------------------------------------------------");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("        ------------------------------------------------------------");
                break;
            } else if (command.equals("list")) {
                System.out.println("        ------------------------------------------------------------");
                for (int i = 1; i < noOfTasks + 1; i++) {
                    System.out.println("        " + i + ". " + tasks.get(i - 1));
                }
                System.out.println("        ------------------------------------------------------------");
            } else {
                System.out.println("        ------------------------------------------------------------");
                System.out.println("        added: " + command);
                System.out.println("        ------------------------------------------------------------");
                tasks.add(command);
                noOfTasks++;
            }
        }
    }
}
