package exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends NarutoException {
    /**
     * Constructs an InvalidCommandException with the specified error message.
     *
     * @param message the error message
     */
    InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_COMMAND;
    }
}
