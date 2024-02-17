package exceptions;

/**
 * Throw if an unrecognised command is input by the user
 */
public class CommandException extends DukeException{
    public CommandException() {
        super();
    }
    public CommandException(String message) {
        super(message);
    }
}
