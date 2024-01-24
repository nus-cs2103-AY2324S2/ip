import java.util.Scanner;
public class Duke {
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

            // Echo the user command
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                // Farewell
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }
        }

        // Close the scanner
        scanner.close();
    }
}