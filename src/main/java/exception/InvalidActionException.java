package exception;

/**
 * Exception thrown when an invalid action is performed.
 */
public class InvalidActionException extends NarutoException {
    /**
     * Constructs a new InvalidActionException with the specified detail message.
     *
     * @param message the detail message
     */
    InvalidActionException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.INVALID_ACTION;
    }
}
