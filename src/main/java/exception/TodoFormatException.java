package exception;

/**
 * Exception indicating an incorrect format for the to-do input.
 */
public class TodoFormatException extends UncleBobException {

    private static final String EXCEPTION_MESSAGE = "Uncle also need to know the message!"
            + "\n\tCorrect Usage: todo <message>";
    public TodoFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
