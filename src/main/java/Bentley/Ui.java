package bentley;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class representing the user interface of the Bentley task management
 * application.
 */
public class Ui {

    // Scanner object to read user input.
    private Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the user input.
     *
     * @return The user input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Bentley\n" + "What can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

}
