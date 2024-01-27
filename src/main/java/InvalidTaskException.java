/**
 * Exception thrown when an invalid task operation is attempted.
 * This can occur in various scenarios such as trying to access a task
 * that doesn't exist or providing an invalid task number.
 */
public class InvalidTaskException extends Exception {

    /**
     * Constructs an InvalidTaskException with a detailed message.
     * This message provides information about what makes the task operation invalid.
     *
     * @param message A string detailing the reason for the exception.
     */
    public InvalidTaskException(String message) {
        super(message); // Call to the superclass (Exception) constructor with the detailed message
    }
}
