package duke.ui;

import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interface interactions
 * in the Duke application, including reading user input and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner to read user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm EchoPilot.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);
    }


    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(goodbyeMessage);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        String errorMessage = "____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________";
        System.out.println(errorMessage);
    }


    /**
     * Displays an error message indicating failure to load tasks from a file.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }


    /**
     * Displays a general message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
