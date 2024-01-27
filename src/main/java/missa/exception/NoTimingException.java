package missa.exception;

/**
 * missa.exception.NoTimingException alerts users when no timing is provided.
 */
public class NoTimingException extends Exception {
    public NoTimingException() {
        super("I am sorry, can I know the timing for this task?");
    }
}
