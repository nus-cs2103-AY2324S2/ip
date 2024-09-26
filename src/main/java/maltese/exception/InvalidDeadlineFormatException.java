package maltese.exception;


/**
 * Represents an exception thrown when the format provided for deadline is wrong.
 */
public class InvalidDeadlineFormatException extends MalteseException {

    /**
     * Constructs a InvalidEventFormatException with no specified detail message.
     */
    public InvalidDeadlineFormatException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the deadline format provided is wrong.";
    }
}
