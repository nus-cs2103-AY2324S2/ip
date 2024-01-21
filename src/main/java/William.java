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
        // To store the task into a list
        List<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");

        while (true) {
            String input = sc.nextLine();
            Commands command = null;

            try {
                command = Commands.valueOf(input);
            } catch (IllegalArgumentException notCommand) {
                // If it is not a command, store as input
                Task perTask = new Task(input);
                tasks.add(perTask);
            }

            if (command != null) {
                switch (command) {
                    case list:
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).getName());
                        }
                        System.out.println("\n");
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
            } else {
                System.out.println("added: " + input + "\n");
            }
        }
    }
}
