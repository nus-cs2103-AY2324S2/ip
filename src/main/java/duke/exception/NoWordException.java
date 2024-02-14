package duke.exception;


/**
 * Represents an exception thrown when a description is empty.
 */
public class NoWordException extends DukeException {

    /**
     * Constructs an EmptyDescriptionException with no specified detail message.
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
        return super.getMessage() + "Please provide a word to search for.";
    }
}
