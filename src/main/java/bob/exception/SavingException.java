package bob.exception;

public class SavingException extends BobException {
    private static final String MESSAGE = "i cant save this task because %s";

    public SavingException(String message) {
        super(String.format(MESSAGE, message));
    }
}
