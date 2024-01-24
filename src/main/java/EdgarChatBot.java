import java.util.Scanner;

public class EdgarChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Edgar.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i] );
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            tasks[taskCount] = userInput;
            taskCount++;

            System.out.println("____________________________________________________________");
            System.out.println("added:" + userInput);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}

