package exceptions;

/**
 * Throw if the arguments to a command input by the user is invalid.
 */
public class ArgumentException extends DukeException{
    public ArgumentException() {
        super();
    }
    public ArgumentException(String message) {
        super(message);
    }
}
