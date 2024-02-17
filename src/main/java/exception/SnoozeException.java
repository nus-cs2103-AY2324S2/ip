package exception;

public class SnoozeException extends DukeException{
    /**
     * Constructs a DukeException with the specified error message.
     */
    public SnoozeException() {
        super("ToDo task cannot be snoozed!");
    }
}
