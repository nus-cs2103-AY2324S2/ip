package exception;

/**
 * The SnoozeException class represents an exception thrown when attempting to snooze a ToDo task.
 * Extends DukeException.
 */
public class SnoozeException extends DukeException {

    /**
     * Constructs a SnoozeException with the specified error message.
     */
    public SnoozeException() {
        super("ToDo task cannot be snoozed!");
    }
}
