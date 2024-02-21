package dav;

import java.util.Scanner;

/**
 * Handles user interface interactions such as greeting, getting user input, and displaying messages.
 */
class Ui {

    /**
     * Displays a greeting message to the user.
     */
    public String greetUser() {
        return "What's up! I'm Dav.\nHow may I help you?";
    }

    /**
     * Gets user input using the provided Scanner.
     * @param scanner Scanner object for reading user input.
     * @return User input as a string.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Displays an error message when there is an issue loading tasks from a file.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }
}

