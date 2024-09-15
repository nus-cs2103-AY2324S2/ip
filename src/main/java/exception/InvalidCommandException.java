package exception;

/**
 * Represents the exception thrown when the command is invalid.
 */
public class InvalidCommandException extends ChronosException {
    /**
     * Constructs a InvalidCommandException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidCommandException(String message) {
        super(message);
    }
}
