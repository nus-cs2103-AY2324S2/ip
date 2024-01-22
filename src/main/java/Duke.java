import java.util.Scanner;
public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int taskCount = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("     Hello! I'm Cleo");
        System.out.println("     What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Check for the exit condition
            if (input.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (input.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add task to the array and echo it back
                if (taskCount < MAX_TASKS) {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("     added: " + input);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("     Maximum tasks reached. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }
        }
        scanner.close();
    }


}
