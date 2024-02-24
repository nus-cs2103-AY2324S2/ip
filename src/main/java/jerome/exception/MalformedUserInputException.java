package jerome.exception;

/**
 * Represents an exception that is thrown when the user input is dirty.
 */
public class MalformedUserInputException extends Exception {
    /**
     * Represents an exception which represents the user dirty input.
     * @param message error that user needs to correct.
     */
    public MalformedUserInputException(String message) {
        super(message);
    }
}
