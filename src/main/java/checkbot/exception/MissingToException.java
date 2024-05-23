package checkbot.exception;

/**
 * Represents an exception where the user does not input the /to argument for an
 * Event.
 */
public class MissingToException extends MissingArgumentException {
    public MissingToException() {
        super("to");
    }
}
