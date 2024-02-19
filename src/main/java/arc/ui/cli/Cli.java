package arc.ui.cli;

import java.util.Scanner;

/**
 * Represents the User Interface component of the Arc application.
 * This class is responsible for all user interactions, including reading user input
 * and displaying messages to the user.
 */
public class Cli {
    /**
     * The name of the chat-bot, used in the welcome message.
     */
    private static final String NAME = "Arc";

    /**
     * The character used to generate borders in the user interface.
     */
    private static final Character BORDER_CHAR = '_';

    /**
     * The length of the border generated in the user interface.
     */
    private static final Integer BORDER_LEN = 60;

    /**
     * Scanner used to read user input.
     */
    private final Scanner scanner;

    /**
     * Constructs a new Ui object.
     * Initializes the scanner used to read user input from the console.
     */
    public Cli() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line border to the user.
     * This is used to separate different parts of the chat-bot messages for clarity.
     */
    public void showLine() {
        System.out.println(String.valueOf(BORDER_CHAR).repeat(BORDER_LEN));
    }

    /**
     * Displays a welcome message to the user at the start of the application.
     */
    public void showWelcome() {
        this.showMessage(String.format("Hello! I'm %s\nWhat can I do for you?", NAME));
    }

    /**
     * Displays an error message to the user.
     * This method is used to show error messages arising from Arc's operations.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        this.showMessage(error);
    }

    /**
     * Displays a message to the user, enclosed within horizontal line borders.
     *
     * @param message The message to be displayed to the user.
     */
    public void showMessage(String message) {
        this.showLine();
        System.out.println(message);
        this.showLine();
    }

    /**
     * Reads a command from the user.
     * Waits for and returns the next line of input from the user.
     *
     * @return The command input by the user as a String.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }
}
