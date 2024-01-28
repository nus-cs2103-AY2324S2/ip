package exception;

/**
 * Exception thrown when a deadline is invalid.
 */
public class InvalidDeadlineException extends NarutoException {

    /**
     * Constructs an InvalidDeadlineException with the specified detail message.
     *
     * @param message the detail message
     */
    InvalidDeadlineException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_DEADLINE;
    }

}
