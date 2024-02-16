package exception;

/**
 * Exception thrown when an duplicate task is encountered.
 */
public class DuplicateTaskException extends NarutoException {

    /**
     * Constructs a new DuplicateTaskException with the specified error message.
     *
     * @param message the error message
     */
    public DuplicateTaskException(String message) {
        super(message);
    }

    /**
     * Returns the type of error associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.DUPLICATE_TASK;
    }
}
