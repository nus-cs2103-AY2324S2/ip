import java.util.Scanner;

public class Duke {

    private static final int MAX_NO_OF_TASKS = 100;
    private static String[] tasks = new String[MAX_NO_OF_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EchoBot.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String userInput;
        while(true) {
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                if (taskCount < MAX_NO_OF_TASKS) {
                    tasks[taskCount] = userInput;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
