package maltese.exception;



/**
 * Represents an exception thrown when a wrong index is provided for a task operation.
 */
public class WrongIndexException extends MalteseException {

    /**
     * Constructs a NoIndexException with no specified detail message.
     */
    public WrongIndexException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the index provided is out of bounds.";
    }
}
