package badgpt.exceptions;

/**
 * Signals that the task list is currently empty.
 */
public class ListEmptyException extends TaskException {

    /**
     * Creates a new ListEmptyException with the specified message.
     *
     * @param message The error message.
     */
    public ListEmptyException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return "There are no tasks.";
    }
}
