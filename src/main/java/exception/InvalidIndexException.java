package exception;

/**
 * Exception thrown when an invalid index is encountered.
 */
public class InvalidIndexException extends NarutoException {
    /**
     * Constructs a new InvalidIndexException with the specified detail message.
     *
     * @param message the detail message
     */
    InvalidIndexException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_INDEX;
    }
}
