package maltese.exception;

/**
 * Represents an exception thrown when the date format is incorrect.
 */
public class WrongDateFormatException extends MalteseException {

    /**
     * Constructs a WrongDateFormatException with no specified detail message.
     */

    public WrongDateFormatException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the 'date' format is wrong. Make sure its in the format "
                + "YYYY-MM-DD";
    }
}


