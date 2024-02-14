package ui;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Ui() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Print out the error message
     * @param err Error thrown from ChatBot
     */
    public static String printError(Exception err) {
        return ("Error: " + err);
    }

    /**
     * Print warning for invalid commands from Users
     */
    public static String invalidCommand() {
        return ("Invalid input. ChatBot can only handle "
                + "'todo', 'deadline', 'event', 'bye', 'list' tasks");
    }
}
