package exceptions;

public class TaskIndexException extends Exception {
    public TaskIndexException(String message) {
        super("Task Index Exception: " + message);
    }
}
