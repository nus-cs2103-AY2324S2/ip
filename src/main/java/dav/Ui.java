package dav;

import java.util.Scanner;

/**
 * Handles user interface interactions such as greeting, getting user input, and displaying messages.
 */
class Ui {

    /**
     * Displays a greeting message to the user.
     */
    public void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
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
     * Displays an exit message to the user.
     */
    public void exit() {
        System.out.println(" Goodbye. ");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue loading tasks from a file.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}

