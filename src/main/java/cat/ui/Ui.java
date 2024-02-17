package cat.ui;

import cat.task.Task;
import cat.ui.response.ErrorResponse;
import cat.ui.response.NoteResponse;
import cat.ui.response.Response;

/**
 * The user interface management class. This class is the centralized interface for input and output to the program.
 */
public class Ui {
    /**
     * Shows the error encountered by the program to the user.
     */
    public static Response showError(Exception e) {
        assert e != null : "The exception must not be null";
        return new ErrorResponse(e);
    }

    /**
     * Shows an error encountered by the program to the user.
     * This version makes the response from a string when the response is not necessarily from an exception call.
     */
    public static Response showError(String e) {
        assert e != null : "The exception string must not be null";
        return new ErrorResponse(e);
    }

    /**
     * Shows a note to the user.
     */
    public static Response showNote(String str) {
        assert str != null : "Note string must not be null";
        return new NoteResponse("The cat hands a note to you", str);
    }

    /**
     * Tells the user that the command used is not recognized.
     */
    public static Response showCommandNotFound(String command) {
        assert command != null : "Command name must not be null";
        return new NoteResponse("The cat tilts its head",
                "It doesn't know what command \"" + command + "\" is.");
    }

    /**
     * Shows an added task.
     *
     * @param task task that was added
     */
    public static Response showAddedTask(Task task) {
        assert task != null : "Task must not be null";
        return new NoteResponse("The cat scratches a mark on the wall and then hands you a receipt",
                "Added task: " + task.describe());
    }

    /**
     * Show the help info to the user
     */
    public static Response showHelp() {
        return new NoteResponse("The cat hands you a note with some useful information",
                showTerminology() + showCommands());
    }

    private static String showTerminology() {
        return "Words wrapped with <> represent required parameters.\n\n";
    }

    /**
     * Show the command the program understands
     */
    private static String showCommands() {
        return "The allowed commands are as follows: \n"
                + "todo <description>\n"
                + "deadline <description> /by <time>\n"
                + "event <description> /from <time> /by <time>\n"
                + "mark <index>\n"
                + "unmark <index>\n"
                + "delete <index>\n"
                + "list\n"
                + "bye";
    }
}
