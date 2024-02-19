package judy.exceptions;

/**
 * A custom exception class for handling exceptions specific to the Duke application.
 */
public class DukeException extends Exception{

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exceptional condition.
     */
    public DukeException(String message) {
        super(message);
    }
}
