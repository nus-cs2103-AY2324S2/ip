package luke.exception;

/**
 * Represents an exception when the task is not in the correct format.
 */
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}
