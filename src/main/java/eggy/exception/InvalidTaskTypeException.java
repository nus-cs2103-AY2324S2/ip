package eggy.exception;

/**
 * Represents an exception when the task type is invalid.
 */
public class InvalidTaskTypeException extends EggyException {
    /**
     * Constructs an InvalidTaskTypeException.
     */
    public InvalidTaskTypeException() {
        super(" There is an invalid task type in the file :-(");
    }
}
