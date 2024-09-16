package badgpt.exceptions;

/**
 * Signals that the task list is currently full.
 */
public class ListFullException extends TaskException {

    /**
     * Creates a new ListFullException with the specified message.
     *
     * @param message The error message.
     */
    public ListFullException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return "dude maybe finish something first";
    }
}
