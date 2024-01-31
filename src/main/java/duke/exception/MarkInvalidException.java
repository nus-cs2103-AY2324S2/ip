package duke.exception;

/**
 * The class representing the exception thrown when the mark or unmark command is invalid.
 * */
public class MarkInvalidException extends DukeException {
    public MarkInvalidException(String command) {
        super("Please enter a valid integer after a " + command + " command.");
    }
}
