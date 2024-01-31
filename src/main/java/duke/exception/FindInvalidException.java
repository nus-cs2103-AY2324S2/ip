package duke.exception;

/**
 * The class representing the exception thrown when the delete command is invalid.
 * */
public class FindInvalidException extends DukeException {
    public FindInvalidException() {
        super("Please enter a valid integer after find.");
    }
}
