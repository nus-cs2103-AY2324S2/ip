package maltese.exception;

/**
 * Represents an exception thrown when user tries to do the same operation on the same indices.
 */
public class DuplicateIndexException extends MalteseException {

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
