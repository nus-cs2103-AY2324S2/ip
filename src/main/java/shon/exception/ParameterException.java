package shon.exception;

/**
 * Represent the exception when an invalid parameter is input for that command.
 */
public class ParameterException extends Exception {
    /**
     * Creates the exception with the given message.
     *
     * @param message The message in the exception.
     */
    public ParameterException(String message) {
        super(message);
    }
}
