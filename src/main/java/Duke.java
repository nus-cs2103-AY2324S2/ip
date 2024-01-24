import java.util.Scanner;
public class Duke {
    // Fixed-size array to store tasks
    private static String[] tasks = new String[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        // Greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duck");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main chat loop
        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Process user input
            if (userInput.equalsIgnoreCase("bye")) {
                // Farewell
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            } else if (userInput.equalsIgnoreCase("list")) {
                // Display the list of tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add the task to the array
                tasks[taskCount++] = userInput;
                // Display confirmation
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        // Close the scanner
        scanner.close();
    }
}