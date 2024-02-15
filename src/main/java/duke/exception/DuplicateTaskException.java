package duke.exception;

/**
 * Represents an exception thrown when a description is empty.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Constructs an DuplicateTaskException with no specified detail message.
     */
    public DuplicateTaskException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "this task is already on your list.";
    }
}