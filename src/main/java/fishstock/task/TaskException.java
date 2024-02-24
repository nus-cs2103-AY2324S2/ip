package fishstock.task;

/**
 * Encapsulates a TaskException.
 */
public class TaskException extends Exception {
    /**
     * Initializes a TaskException.
     *
     * @param message The error message.
     */
    public TaskException(String message) {
        super(message);
    }
}
