package checkbot.exception;

/**
 * Represents an exception where the user does not input the /by argument for a
 * Deadline.
 */
public class MissingDeadlineException extends MissingArgumentException {
    public MissingDeadlineException() {
        super("by");
    }
}
