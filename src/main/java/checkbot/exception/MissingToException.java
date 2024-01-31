package checkbot.exception;

public class MissingToException extends MissingArgumentException {
    public MissingToException() {
        super("to");
    }
}
