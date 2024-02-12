package bob.exception;

public class InvalidCommandException extends BobException {
    private static final String MESSAGE = "what";

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
