package exception;

/**
 * Exception thrown when an invalid event is encountered.
 */
public class InvalidEventException extends NarutoException {
    /**
     * Constructs a new InvalidEventException with the specified detail message.
     *
     * @param message the detail message
     */
    InvalidEventException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.INVALID_EVENT;
    }
}
