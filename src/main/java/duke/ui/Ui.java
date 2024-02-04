package duke.ui;

import java.util.Scanner;

/**
 * The Ui class handles user interaction and provides formatted output to the console.
 * It includes methods for greeting the user, formatting replies, displaying errors,
 * and obtaining user input.
 */
public class Ui {

    /**
     * Scanner object for reading user input.
     */
    private final Scanner myScannerObj;

    /**
     * Horizontal line used for formatting output.
     */
    private final String horizontalLine = "____________________________________________________________\n";

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        this.myScannerObj = new Scanner(System.in);
    }

    /**
     * Greets the user with an introductory message.
     */
    public void greetUser() {
        this.formatReply("Hello! I'm Zenify\n" + " What can I do for you?");
    }

    /**
     * Formats and prints a reply to the console.
     *
     * @param raw The raw message to be formatted and displayed.
     */
    public void formatReply(String raw) {
        System.out.println(this.horizontalLine + " " + raw + "\n" + this.horizontalLine);
    }

    /**
     * Formats and prints an error message to the console.
     *
     * @param raw The raw error message to be formatted and displayed.
     */
    public void formatError(String raw) {
        System.err.println(this.horizontalLine + " " + raw + "\n" + this.horizontalLine);
    }

    /**
     * Displays a farewell message to the user upon exiting the application.
     */
    public void exit() {
        this.formatReply("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayError(String errorMessage) {
        this.formatError("Error: " + errorMessage);
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input as a String.
     */
    public String getInput() {
        return myScannerObj.nextLine();
    }

    /**
     * Checks if there is more input from the user.
     *
     * @return True if there is more input, false otherwise.
     */
    public boolean next() {
        return myScannerObj.hasNextLine();
    }

    /**
     * Closes the Scanner to release system resources.
     */
    public void closeScanner() {
        myScannerObj.close();
    }

}
