package exception;

/**
 * Exception thrown when a todo is empty.
 */
public class EmptyTodoException extends NarutoException {

    /**
     * Constructs an EmptyTodoException with the specified detail message.
     *
     * @param message the detail message
     */
    EmptyTodoException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public NarutoException.ErrorType getErrorType() {
        return ErrorType.EMPTY_TODO;
    }
}
