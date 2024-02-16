package duke.exception;

/**
 * The class representing the exception thrown when the mark or unmark command is invalid.
 * */
public class MarkInvalidException extends DukeException {
    /* The first part of the error message to be displayed to the user. */
    public static final String ERROR_MESSAGE_ONE = "Please enter a valid integer after a ";
    /* The second part of the error message to be displayed to the user. */
    public static final String ERROR_MESSAGE_TWO = " command.";

    public MarkInvalidException(String command) {
        super(ERROR_MESSAGE_ONE + command + ERROR_MESSAGE_TWO);
    }
}
