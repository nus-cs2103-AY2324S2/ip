package maltese;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the maltese application.
 */
public class Ui {
    /**
     * Prompts the user for input and retrieves the entered command.
     *
     * @return The user-entered command as a String.
     */
    public String getUserInput() {
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



