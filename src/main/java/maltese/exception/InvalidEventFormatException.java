package maltese.exception;


/**
 * Represents an exception thrown when the format provided for event is wrong.
 */
public class InvalidEventFormatException extends MalteseException {

    /**
     * Constructs a InvalidEventFormatException with no specified detail message.
     */
    public InvalidEventFormatException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the event format provided is wrong.";
    }
}
