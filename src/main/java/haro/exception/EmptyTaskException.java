package haro.exception;

/**
 * Exception thrown when a task name is not given.
 */
public class EmptyTaskException extends Exception {
    public EmptyTaskException(String message) {
        super(message);
    }
}
