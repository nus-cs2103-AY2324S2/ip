package pan.exceptions;

public class TaskIndexException extends Exception {
    /**
     * Creates a TaskIndexException instance.
     * 
     * @param message error message of the given exception
     */
    public TaskIndexException(String message) {
        super("Task Index Exception: " + message);
    }
}
