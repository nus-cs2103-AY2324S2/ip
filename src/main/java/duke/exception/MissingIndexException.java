package duke.exception;
/**
 * Represents an exception thrown when there is a missing index.
 */
public class MissingIndexException extends DukeException {

    /**
     * Constructs a MissingIndexException with no specified detail message.
     */
    public MissingIndexException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "no index were provided.";
    }
}