package yoda.exceptions;
/**
 * Exception thrown when an invalid task operation is attempted.
 * This can occur in various scenarios such as trying to access a task
 * that doesn't exist or providing an invalid task number.
 */
public class InvalidTaskException extends Exception {

    /**
     * Constructs an InvalidTaskException with a default message.
     */
    public InvalidTaskException() {
        super("Valid task number, provide you must.");
    }
}
