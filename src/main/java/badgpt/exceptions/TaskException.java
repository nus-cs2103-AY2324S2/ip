package badgpt.exceptions;

/**
 * General class of exceptions that arise from tasks and operations on them.
 */
public class TaskException extends BadException {
    /**
     * Creates a new TaskException with the specified message.
     *
     * @param message The error message.
     */
    public TaskException(String message) {
        super(message);
    }
}
