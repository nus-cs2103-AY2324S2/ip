package duke;

import java.util.Scanner;

/**
 * Manages user command line interface interactions for the Duke application.
 * This class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {
    /**
     * Scanner to read input from the console.
     */
    private Scanner scanner;

    /**
     * Constructs a new Ui instance. Initializes the scanner used to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user at the start of the application.
     */
    public void showGreeting() {
        System.out.println("Hello! I'm HughJazz");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user when exiting the application.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command from the user.
     *
     * @return The next line of input from the user, trimmed of leading and trailing whitespace.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}

