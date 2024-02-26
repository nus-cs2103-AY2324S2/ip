package common;

/**
 * Represents an exception specific to this program.
 */
public class DukeException extends Exception {
    /**
     * Creates an exception with the provided error message.
     *
     * @param message The error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
