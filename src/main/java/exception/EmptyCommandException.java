package exception;

/**
 * Exception thrown when an empty command is encountered.
 */
public class EmptyCommandException extends NarutoException {

    /**
     * Constructs a new EmptyCommandException with the specified error message.
     *
     * @param message the error message
     */
    EmptyCommandException(String message) {
        super(message);
    }

    /**
     * Returns the type of error associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_COMMAND;
    }
}
