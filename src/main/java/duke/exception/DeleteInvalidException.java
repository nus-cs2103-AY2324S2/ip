package duke.exception;

/**
 * The class representing the exception thrown when the delete command is invalid.
 * */
public class DeleteInvalidException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Please enter a valid integer after delete.";

    public DeleteInvalidException() {
        super(ERROR_MESSAGE);
    }
}
