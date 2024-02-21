package maltese.exception;


/**
 * Represents an exception thrown when no file paths were given for change.
 */
public class NoFilePathException extends MalteseException {

    /**
     * Constructs an NoWordDescriptionException with no specified detail message.
     */
    public NoFilePathException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "please provide the file path you wished to change to";
    }
}
