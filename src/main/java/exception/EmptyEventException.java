package exception;

/**
 * Exception thrown when an event is empty.
 */
public class EmptyEventException extends NarutoException {
    /**
     * Constructs a new EmptyEventException with the specified detail message.
     *
     * @param message the detail message
     */
    EmptyEventException(String message) {
        super(message);
    }

    /**
     * Returns the error type of this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_EVENT;
    }
}
