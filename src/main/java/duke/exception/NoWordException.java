package duke.exception;


/**
 * Represents an exception thrown when no words were given for find.
 */
public class NoWordException extends DukeException {

    /**
     * Constructs an NoWordDescriptionException with no specified detail message.
     */
    public NoWordException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "please provide a word to search for.";
    }
}
