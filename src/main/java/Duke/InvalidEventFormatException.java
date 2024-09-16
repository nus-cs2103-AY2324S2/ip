package duke;

/**
 * Represents an exception thrown when the user tries to create an event task without providing both the
 * required "/from" and "/to" arguments in the Duke application.
 */
public class InvalidEventFormatException extends DukeException {

    /**
     * Constructs a new InvalidEventFormatException with an informative error message.
     */
    public InvalidEventFormatException() {
        super("/from and /to arguments needed for event");
    }
}
