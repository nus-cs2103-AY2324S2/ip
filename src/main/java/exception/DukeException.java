package exception;

/**
 * Represents an exception specific to the Duke application.
 * This exception can be thrown when there is an error or unexpected condition
 * in the Duke application.
 */
public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}
