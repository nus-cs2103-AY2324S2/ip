package duke;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the Duke application.
 */
public class Ui {

    /**
     * Displays a welcome message when the Duke application starts.
     */
    public void showWelcome() {
        System.out.println("Hello I'm NoisyChatter");
        System.out.println("What can I do for you?");
    }

    /**
     * Prompts the user for input and retrieves the entered command.
     *
     * @return The user-entered command as a String.
     */
    public String getUserInput() {
        System.out.print("Enter command: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays an error message when there is an issue loading data from a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading data from file.");
    }
}


