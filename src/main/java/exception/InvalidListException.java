package exception;

/**
 * Represents the exception thrown when the list command is invalid.
 */
public class InvalidListException extends ChronosException {
    /**
     * Constructs a InvalidListException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidListException(String message) {
        super(message);
    }
}
