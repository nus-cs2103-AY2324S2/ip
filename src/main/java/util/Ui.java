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
    public String printBye() {
        String bye = "Goodbye! Till we meet again ~ \n";
        System.out.println(bye);
        return bye;
    }

    /**
     * Prints a line separator.
     */
    public String printLine() {
        String line = "------------------------------------ \n";
        System.out.println();
        return line;
    }

    /**
     * Prints a message for marking tasks as done.
     */
    public String printMark() {
        return ("Great job agent 47. Marking this task as DONE: \n");
    }

    /**
     * Prints a message for marking tasks as not done.
     */
    public String printUnmark() {
        return ("Alright, marking this task as NOT DONE :( : \n");
    }

    /**
     * Prints an error message for unknown commands.
     */
    public String printUnknown() {
        return ("Sorry, unknown command given. Please try again. \n");
    }

    /**
     * Prints an error message for creating deadlines.
     *
     * @param e The exception creating the error.
     */
    public String printDeadlineError(Exception e) {
        String errorMsg = "";
        if (e instanceof IndexOutOfBoundsException) {
            errorMsg += ("OH NOES!! Do enter the deadline(YYYY-MM-DD) correctly with: /by [DEADLINE]. \n");
        } else if (e instanceof IllegalArgumentException) {
            errorMsg += ("Uh oh. Something went wrong with your input! \n");
        } else if (e instanceof DateTimeParseException) {
            errorMsg += ("WOOPS! Enter the correct date format: YYYY-MM-DD \n");
        }
        return errorMsg;
    }

    /**
     * Prints an error message for creating events.
     *
     * @param e The exception creating the error.
     */
    public String printEventError(Exception e) {
        String errorMsg = "";
        if (e instanceof IndexOutOfBoundsException) {
            errorMsg += (
                    "OH NOES!! Do enter the event dates(YYYY-MM-DD) correctly with: /from [start] /to [end]. \n");
        } else if (e instanceof IllegalArgumentException) {
            errorMsg += ("Uh oh. Something went wrong with your input! \n");
        } else if (e instanceof DateTimeParseException) {
            errorMsg += ("WOOPS! Enter the correct date format: YYYY-MM-DD \n");
        }
        return errorMsg;
    }

    /**
     * Prints an error message for empty task descriptions.
     */
    public String printEmptyDescription() {
        return "Oh dear! The description of task cannot be empty! \n";
    }

    /**
     * Prints an error message for operations.
     *
     * @param e The exception creating the error.
     */
    public String printOperationError(Exception e) {
        String errorMsg = "";
        if (e instanceof NumberFormatException) {
            errorMsg += ("Oh dear! Please enter a number instead ^.^ \n");
        } else if (e instanceof NullPointerException | e instanceof IndexOutOfBoundsException) {
            errorMsg += ("Oopsies! Please enter a valid task number ^.^ \n");
        }
        return errorMsg;
    }
}
