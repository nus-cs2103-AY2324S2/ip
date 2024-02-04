package util;

import java.time.format.DateTimeParseException;

/**
 * Deals with all interactions with the user.
 *
 * @author Tan Qin Yong
 */
public class Ui {
    public Ui() {};

    /**
     * Prints the default greeting message.
     */
    public void printGreeting() {
        String name = "Felix";
        String logo = " _____    _ _      \n"
                + "|  ___|__| (_)_  __\n"
                + "| |_ / _ \\ | \\ \\/ /\n"
                + "|  _|  __/ | |>  < \n"
                + "|_|  \\___|_|_/_/\\_\\\n";
        System.out.println(logo);
        System.out.println("------------------------------------");
        System.out.println("I'm " + name + "!");
        System.out.println("What may I do for you today? ");
        System.out.println("------------------------------------");
    }

    /**
     * Prints a goodbye message
     */
    public void printBye() {
        System.out.println("Goodbye! Till we meet again ~");
    }

    /**
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println("------------------------------------");
    }

    /**
     * Prints a message for marking tasks as done.
     */
    public void printMark() {
        System.out.println("Great job agent 47. Marking this task as DONE: ");
    }

    /**
     * Prints a message for marking tasks as not done.
     */
    public void printUnmark() {
        System.out.println("Alright, marking this task as NOT DONE :( : ");
    }

    /**
     * Prints an error message for unknown commands.
     */
    public void printUnknown() {
        System.out.println("Sorry, unknown command given. Please try again.");
    }

    /**
     * Prints an error message for creating deadlines.
     *
     * @param e The exception creating the error.
     */
    public void printDeadlineError(Exception e) {
        if (e instanceof IndexOutOfBoundsException) {
            System.out.println("OH NOES!! Do enter the deadline(YYYY-MM-DD) correctly with: /by [DEADLINE]. ");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println("Uh oh. Something went wrong with your input!");
        } else if (e instanceof DateTimeParseException) {
            System.out.println("WOOPS! Enter the correct date format: YYYY-MM-DD");
        }
    }

    /**
     * Prints an error message for creating events.
     *
     * @param e The exception creating the error.
     */
    public void printEventError(Exception e) {
        if (e instanceof IndexOutOfBoundsException) {
            System.out.println("OH NOES!! Do enter the event dates(YYYY-MM-DD) correctly with: /from [start] /to [end]. ");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println("Uh oh. Something went wrong with your input!");
        } else if (e instanceof DateTimeParseException) {
            System.out.println("WOOPS! Enter the correct date format: YYYY-MM-DD");
        }
    }

    /**
     * Prints an error message for empty task descriptions.
     */
    public void printEmptyDescription() {
        System.out.println("Oh dear! The description of task cannot be empty!");
    }

    /**
     * Prints an error message for operations.
     *
     * @param e The exception creating the error.
     */
    public void printOperationError(Exception e) {
        if (e instanceof NumberFormatException) {
            System.out.println("Oh dear! Please enter a number instead ^.^");
        } else if (e instanceof NullPointerException | e instanceof  IndexOutOfBoundsException) {
            System.out.println("Oopsies! Please enter a valid task number ^.^");
        }
    }
}
