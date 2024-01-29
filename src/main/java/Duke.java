import java.util.ArrayList; // Import the ArrayList class
import java.util.Scanner; // Import the Scanner class

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        ArrayList<String> tasks = new ArrayList<>(); // Create an ArrayList to store user inputs

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine(); // Read user input

            if (input.equalsIgnoreCase("bye")) { // Check if input is "bye"
                System.out.println("Bye. Hope to see you again soon!");
                break; // Break the loop if user says bye
            }

            if (input.equalsIgnoreCase("read")) { // Check if input is "list"
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input); // Add the input to the list of tasks
                System.out.println("added: " + input);
            }
        }

        scanner.close(); // Close the scanner
    }
}
