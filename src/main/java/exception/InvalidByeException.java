package exception;

/**
 * Represents the exception thrown when the bye command is invalid.
 */
public class InvalidByeException extends ChronosException {
    /**
     * Constructs a InvalidByeException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidByeException(String message) {
        super(message);
    }
}
