package exception;

/**
 * Represents the exception thrown when the task number is missing.
 */
public class MissingTaskNumberException extends ChronosException {
    /**
     * Constructs a MissingTaskNumberException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    MissingTaskNumberException(String message) {
        super(message);
    }
}
