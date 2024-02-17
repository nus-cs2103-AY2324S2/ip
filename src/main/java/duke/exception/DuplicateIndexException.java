package duke.exception;

/**
 * Represents an exception thrown when a description is empty.
 */
public class DuplicateIndexException extends DukeException {

    /**
     * Constructs an DuplicateIndexException with no specified detail message.
     */
    public DuplicateIndexException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "there is repeat of indices listed for this operation.";
    }
}
