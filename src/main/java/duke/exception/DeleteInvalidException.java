package duke.exception;

/**
 * The class representing the exception thrown when the delete command is invalid.
 * */
public class DeleteInvalidException extends DukeException {
    public DeleteInvalidException() {
        super("Please enter a valid integer after delete.");
    }
}
