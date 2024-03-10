package micromanager.exceptions;

/**
 * DukeException class represents an exception specific to the Duke application.
 * It extends the Exception class and provides a constructor to create Duke-specific exceptions.
 */
public class DukeException extends Exception {
    private String errorMessage;

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param errorMessage The error message for the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
