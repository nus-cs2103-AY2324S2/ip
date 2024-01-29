import java.util.Scanner; // Import the Scanner class

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Hello, I'm Jelly!");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine(); // Read user input
            if (input.equalsIgnoreCase("bye")) { // Check if input is "bye"
                System.out.println("Byeee!");
                break; // Break the loop if user says bye
            }
            System.out.println("Echo: " + input); // Echo the user input
        }

        scanner.close(); // Close the scanner
    }
}
