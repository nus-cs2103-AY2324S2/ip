package panda.exception;

public class OutOfBoundsException extends PandaException {
    /**
     * Constructs a new OutOfBoundsException with a default message.
     */
    public OutOfBoundsException() {
        super("OOPS! The item you want to mark doesn't exist. Use the 'list' command to check your current tasks.");
    }
}
