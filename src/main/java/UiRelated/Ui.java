package UiRelated;

import java.util.Scanner;

/**
 * The Ui class handles user interactions by displaying messages and getting user input.
 */
public class Ui {
    private static final String AVAILABLE_COMMAND = "Notes: \n"
                                                  + "-Replace your <task> with your task name\n"
                                                  + "-mm is minute and MM is month\n\n"
                                                  + "Now there are 3 ways to add a Task to list"
                                                  + "\n\t1.todo <task>\n\t"
                                                  + "2.deadline <task> by <MM-dd hh:mm am/pm or MM-dd> "
                                                  + "\n\t3.event <task> followed by \n\t\t"
                                                  + "3.1 hh:mm am/pm to hh:mm am/pm\n\t\t"
                                                  + "3.2 MM/dd hh:mm am/pm to hh:mm am/pm\n";
    private static final String AVAILABLE_LIST_OPERATION = "Here is what you can do with the tasks "
                                                         + "once added to the list:"
                                                         + "\n\t 1.mark <the number of task on the list>\n\t "
                                                         + "2.unmark <the number of task on the list>\n\t "
                                                         + "3.remove <the number of task on the list>\n\t "
                                                         + "4.list (to show the current list)\n\t"
                                                         + "5.find <keyword>\n\t"
                                                         + "6.clearl (to clear the list)";
    @SuppressWarnings("checkstyle:SingleSpaceSeparator")
    private static final String WELCOME_MESSAGE = "HASSNT:\n"
                                                + "Hello! I'm App.HASSTNT "
                                                + "What can I do for you?\n"
                                                + "to see what I can do type --commands\n"
                                                + "-- our date format "
                                                + "starts with month "
                                                + "instead of day (i.e., mm-dd) --";
    private final Scanner scanner;


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
     * Gets user input from the console.
     *
     * @return The user input as a trimmed and lowercased string.
     */

    public String showMessages(String input) {
        return input;
    }

    /**
     * Displays available commands and list operations.
     */

    public String displayCommand() {
        System.out.println(AVAILABLE_COMMAND);
        System.out.println(AVAILABLE_LIST_OPERATION);
        return AVAILABLE_COMMAND + "\n" + AVAILABLE_LIST_OPERATION;
    }
}
