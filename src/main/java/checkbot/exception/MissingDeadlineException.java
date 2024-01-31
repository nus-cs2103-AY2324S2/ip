package checkbot.exception;

public class MissingDeadlineException extends MissingArgumentException {
    public MissingDeadlineException() {
        super("by");
    }
}
