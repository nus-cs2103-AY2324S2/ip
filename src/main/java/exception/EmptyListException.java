package exception;

/**
 * Exception thrown when the current list is empty.
 */
public class EmptyListException extends NarutoException {
    /**
     * Constructs a new EmptyListException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyListException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.EMPTY_LIST;
    }
}
