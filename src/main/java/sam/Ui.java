package sam;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    Scanner scanner;

    /**
     * Constructs a new Ui object.
     *
     * Initializes the Ui object by creating a new Scanner object to read input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user input.
     *
     * Creates a new Scanner object to read input from the console and waits for the user to input a command.
     * Returns the command entered by the user as a string.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message.
     *
     * Prints a greeting message along with a logo to the console.
     */
    public void greet() {
        String logo =
                "  ______\n" +
                " |           /  \\ \n" +
                " |______    /____\\     / \\    / \\\n" +
                "        |  /      \\   /   \\  /   \\\n" +
                "  ______| /        \\ /     \\/     \\\n";
        String greetMessage = "Hello! I'm Sam\n" +
                "How can I help you?";
        System.out.println(logo);
        System.out.println(greetMessage);
    }

    /**
     * Shows the error message.
     *
     * @param err The error message.
     */
    public void showError(String err) {
        System.err.println(err);
    }

    /**
     * Shows the error message when error loading file.
     */
    public void showLoadingError() {
        System.err.println("Error reading tasks from file.");
    }
}
