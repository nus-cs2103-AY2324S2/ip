package duke.exception;

/**
 * Represents an exception thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructs an UnknownCommandException with no specified detail message.
     */
    public UnknownCommandException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "I didn't understand that command.";
    }
}
