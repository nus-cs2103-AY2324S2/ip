package panda.exception;

public class UnknownCommandException extends PandaException {
    /**
     * Constructs a new UnknownCommandException with a default message.
     */
    public UnknownCommandException() {
        super("OOPS! Panda doesn't have that command.");
    }
}
