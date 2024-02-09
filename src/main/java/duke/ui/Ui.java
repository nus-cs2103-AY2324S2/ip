package duke.ui;

import java.util.Scanner;

/**
 * Ui class handles user interface interactions.
 * It provides methods for displaying messages, reading user input, and showing error messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a message indicating a loading error.
     */
    public void showLoadingError() {
        System.out.println("loading error occurred");
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a line separator to improve readability of console output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
