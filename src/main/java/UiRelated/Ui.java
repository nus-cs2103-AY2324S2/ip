package UiRelated;

import java.util.Scanner;

/**
 * The Ui class handles user interactions by displaying messages and getting user input.
 */
public class Ui {
    private final Scanner scanner;
    private static final String AVAILABLE_COMMAND = "Now there are 3 ways to add a Task to list (Replace your <task> with your task name:\n\t1.todo <task>\n\t2.deadline <task> by <MM-dd hh:mm am/pm or MM-dd> (where mm is minute and MM is month)\n\t3.event <task>\n\t\t3.1event <task> <mm:hh am/pm> to <mm:hh am/pm> \n\t\t3.2event <task> <MM/dd> <mm:hh am/pm> to <mm:hh am/pm>";
    private static final String AVAILABLE_LIST_OPERATION = "Here is what you can do with the tasks once added to the list:\n\t 1.mark (to mark a task) <the number of task on the list>\n\t 2.unmark (to unmark a task) <the number of task on the list>\n\t 3.remove (to remove a task) <the number of task on the list>\n\t 4.list (to show the current list)";
    private static final String WELCOME_MESSAGE = "HASSNT:\n" +
            "Hello! I'm App.HASSTNT " + "What can I do for you?\n" + "to see what I can do type --commands\n" + "-- our date format starts with month instead of day (i.e., mm-dd) --";
    private static final String GOODBYE_MESSAGE = "HASSNT:\n" +
            "Bye. Hope to see you again soon!";

    /**
     * Constructs a Ui object that initializes a scanner.
     */
    public Ui() {
        // initialize a scanner
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcomeMessage() {

        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Displays the goodbye message.
     */
    public void displayByeMessage() {

        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayErrorMessage(String errorMessage) {

        System.out.println(errorMessage);
    }

    /**
     * Displays a message.
     *
     * @param s The message to be displayed.
     */
    public void display(String s) {

        System.out.println(s);
    }

    /**
     * Gets user input from the console.
     *
     * @return The user input as a trimmed and lowercased string.
     */
    public String getUserInput() {
        // Prompt the user to enter input
        System.out.print("Your input: ");
        // Read the user input as a line of text
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Displays available commands and list operations.
     */
    public void displayCommand() {
        System.out.println(AVAILABLE_COMMAND);
        System.out.println(AVAILABLE_LIST_OPERATION);
    }
}
