package duke.exception;

<<<<<<< HEAD
/**
 * Represents an exception thrown when no index is provided for a task operation.
 */
=======
>>>>>>> A-CodingStandard
public class NoIndexException extends DukeException {

    /**
     * Constructs a NoIndexException with no specified detail message.
     */
    public NoIndexException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "Please provide the task index.";
    }
}
