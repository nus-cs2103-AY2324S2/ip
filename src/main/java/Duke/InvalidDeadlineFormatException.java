package duke;

/**
 * Represents an exception thrown when the user attempts to create a deadline task without providing the
 * required "/by" argument in the Duke application.
 */
public class InvalidDeadlineFormatException extends DukeException {

    /**
     * Constructs a new InvalidDeadlineFormatException with a clarifying error message.
     */
    public InvalidDeadlineFormatException() {
        super("/by argument needed for deadline");
    }
}
