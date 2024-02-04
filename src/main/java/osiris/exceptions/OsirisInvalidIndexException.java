package osiris.exceptions;

/**
 * The OsirisInvalidIndexException class represents an exception related to invalid task indices .
 */
public class OsirisInvalidIndexException extends OsirisException {

    /**
     * Constructs a new OsirisInvalidIndexException with the specified invalid index.
     *
     * @param index The invalid index causing the exception.
     */
    public OsirisInvalidIndexException(int index) {
        super("No task with index " + (index) + ". Enter 'list' to view tasks.");
    }
}
