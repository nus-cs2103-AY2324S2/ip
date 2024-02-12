package bob;

public class InvalidEventException extends BobException {
    private static final String MESSAGE = "wow your event ends before it starts";

    public InvalidEventException() {
        super(MESSAGE);
    }
}
