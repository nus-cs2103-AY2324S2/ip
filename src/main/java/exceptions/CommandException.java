package exceptions;

/**
 * Signals an error caused by unrecognised command words input by the user.
 */
public class CommandException extends DukeException {
    public CommandException() {
        super();
    }
    public CommandException(String message) {
        super(message);
    }
}
