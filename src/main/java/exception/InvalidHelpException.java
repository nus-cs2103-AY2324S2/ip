package exception;

/**
 * Represents the exception thrown when the help command is invalid.
 */
public class InvalidHelpException extends ChronosException {
    /**
     * Constructs a InvalidHelpException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    InvalidHelpException(String message) {
        super(message);
    }
}
