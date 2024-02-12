package exception;

/**
 * Represents the exception thrown when the keyword(s) is/are not provided by the user.
 */
public class MissingKeywordException extends ChronosException {
    /**
     * Constructs a MissingKeywordException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    MissingKeywordException(String message) {
        super(message);
    }
}
