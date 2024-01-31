package osiris.task;

/**
 * The InvalidTaskException class represents an exception that is thrown when an invalid task name is assigned.
 */
public class InvalidTaskException extends Exception {

    /**
     * Retrieves the string representation of the exception.
     *
     * @return The string representation of the exception.
     */
    @Override
    public String toString() {
        return "Invalid Task Name assigned.";
    }

    /**
     * Retrieves the message associated with the exception.
     *
     * @return The message associated with the exception.
     */
    @Override
    public String getMessage() {
        return "Invalid Task Name assigned.";
    }
}
