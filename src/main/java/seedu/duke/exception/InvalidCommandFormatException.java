package seedu.duke.exception;

/**
 * Represents an exception when user key in command in invalid format
 */
public class InvalidCommandFormatException extends DukeException {
    /**
     * Constructor of InvalidCommandFormatException
     *
     * @param message The error message to be shown
     */
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
