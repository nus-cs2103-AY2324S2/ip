package duke.exception;

/**
 * Represents an exception thrown when a description is empty.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructs an EmptyDescriptionException with no specified detail message.
     */
    public EmptyDescriptionException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the description cannot be empty.";
    }
}

