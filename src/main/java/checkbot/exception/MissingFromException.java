package checkbot.exception;

public class MissingFromException extends MissingArgumentException {
    public MissingFromException() {
        super("from");
    }
}
