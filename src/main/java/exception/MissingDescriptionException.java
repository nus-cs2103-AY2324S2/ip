package exception;

/**
 * Represents the exception thrown when the task description is missing.
 */
public class MissingDescriptionException extends ChronosException {
    /**
     * Constructs a MissingDescriptionException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    MissingDescriptionException(String message) {
        super(message);
    }
}
