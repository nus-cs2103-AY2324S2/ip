package duke.exception;


/**
 * Represents an exception thrown when no index is provided for a task operation.
 */
public class InvalidEventFormatException extends DukeException {

    /**
     * Constructs a NoIndexException with no specified detail message.
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
        return super.getMessage() + "Your event format is wrong.";
    }
}
