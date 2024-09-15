package exception;

/**
 * Represents the exception thrown when the deadline task is invalid.
 */
public class InvalidDeadlineException extends ChronosException {
    /**
     * Constructs a InvalidDeadlineException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidDeadlineException(String message) {
        super(message);
    }
}
