package exception;

/**
 * Represents the exception thrown when the event details provided are invalid.
 */
public class InvalidEventException extends ChronosException {
    /**
     * Constructs a InvalidEventException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidEventException(String message) {
        super(message);
    }
}
