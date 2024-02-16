package duke.exception;

/**
 * The class representing the exception thrown when the delete command is invalid.
 * */
public class FindInvalidException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Please enter a valid integer after find.";

    public FindInvalidException() {
        super(ERROR_MESSAGE);
    }
}
