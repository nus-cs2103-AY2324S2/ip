package exception;

/**
 * Represents the exception thrown when the keyword(s) is/are not found.
 */
public class KeywordNotFoundException extends ChronosException {
    /**
     * Constructs a KeywordNotFoundException object with the given error message.
     *
     * @param message Error message of the exception.
     */
    KeywordNotFoundException(String message) {
        super(message);
    }
}
