package jav.exception;

/**
 * InvalidParamException signals that an invalid parameter was given.
 *
 * This exception is thrown when reading user's inputs.
*/
public class InvalidParamException extends RuntimeException {
    /**
     * Constructs a new InvalidParamException.
     *
     * @param errorMsg a string containing the error message.
     * @param exception the exception.
     * @return the new InvalidParamException.
     */
    public InvalidParamException(String errorMsg, Throwable exception) {
        super(errorMsg, exception);
    }
}
