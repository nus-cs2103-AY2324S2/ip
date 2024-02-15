package jav.exception;

/**
 * InvalidCommandException signals that an invalid command was given.
 *
 * This exception is thrown when reading user's inputs.
*/
public class InvalidCommandException extends RuntimeException {
    /**
     * Constructs a new InvalidCommandException.
     *
     * @param errorMsg a string containing the error message.
     * @param exception the exception.
     * @return the new InvalidCommandException.
     */
    public InvalidCommandException(String errorMsg, Throwable exception) {
        super(errorMsg, exception);
    }
}
