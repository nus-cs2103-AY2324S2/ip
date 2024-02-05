package duke.exception;

/**
 * Represents an exception specific to the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
