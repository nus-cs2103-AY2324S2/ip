package duke;

import java.util.Scanner;

/**
 * Ui class to handle all I/O with user
 */
public class Ui {
    private static final String NAME = "MR. WONG";
    private static final String LINE = "_________________________________";
    private Scanner scanner;

    /**
     * Creates an instance of Ui
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Scans input to read the next command
     * @return The user input
     */
    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Displays an error message
     * @param message
     */
    public void showError(String message) {
        say(message);
    }

    /**
     * Displays an error message when unable to load from file.
     */
    public void showLoadingError() {
        showError("You do not have a task list saved yet. Let me create one :)");
    }

    /**
     * Prints a message.
     * @param message
     */
    public void say(String message) {
        System.out.println(message);
    }

    /**
     * Displays the current task list.
     * @param list List to display.
     */
    public void showList(TaskList list) {
        say("Here are the tasks in your list:\n" + list.toString());
    }

    /**
     * Welcome message for the user.
     */
    public void showWelcome() {
        System.out.println("Hey man. I'm " + NAME +"\nWhat can I do for you?");
    }

    /**
     * Prints horizontal bar to seperate responses.
     */
    public void showLine() {
        System.out.println(LINE);
    }

}
