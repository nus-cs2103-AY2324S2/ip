package exceptions;

/**
 * Signals an error caused by invalid arguments input by the user.
 */
public class ArgumentException extends DukeException {
    public ArgumentException() {
        super();
    }
    public ArgumentException(String message) {
        super(message);
    }
}
