package checkbot.exception;

/**
 * Represents an exception where the user does not input the /from argument for
 * an Event.
 */
public class MissingFromException extends MissingArgumentException {
    public MissingFromException() {
        super("from");
    }
}
