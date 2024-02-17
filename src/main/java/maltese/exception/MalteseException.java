package maltese.exception;

/**
 * Represents a generic exception in the maltese application.
 */

public class MalteseException extends Exception {
    public MalteseException() {
        super();
    }

    /**
     * Gets a default error message for the exception.
     *
     * @return A string representing the default error message.
     */
    @Override
    public String getMessage() {
        return "Unfortunately, ";
    }
}
