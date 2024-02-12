package bob.exception;

public class EmptyDescriptionException extends BobException {
    private static final String MESSAGE = "%s what";

    public EmptyDescriptionException(String command) {
        super(String.format(MESSAGE, command));
    }
}
