import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Declare Scanner instance

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatbotName = "Jamie";
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");

        while (true) {
            System.out.print(">> ");
            String userInput = scanner.nextLine();
            System.out.println(userInput);

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        scanner.close(); // Close the Scanner to avoid resource leaks
    }
}
