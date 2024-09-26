package maltese.exception;


/**
 * Represents an exception thrown when the format provided for event is wrong.
 */
public class InvalidFilePathException extends MalteseException {

    /**
     * Constructs a InvalidFilePathException with no specified detail message.
     */
    public InvalidFilePathException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the file path you provided was invalid.";
    }
}
