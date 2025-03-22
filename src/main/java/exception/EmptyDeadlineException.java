package exception;

/**
 * Exception thrown when a deadline task is created without a specified deadline.
 */
public class EmptyDeadlineException extends NarutoException {
    /**
     * Constructs a new EmptyDeadlineException with the specified error message.
     *
     * @param message the error message
     */
    EmptyDeadlineException(String message) {
        super(message);
    }

    /**
     * Returns the type of error associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_DEADLINE;
    }
}
