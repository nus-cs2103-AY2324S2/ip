package bob.exception;

/**
 * Represents an error indicating that an invalid command has been given by the user.
 * An <code>InvalidCommandException</code> object corresponds to an error indicating
 * that an undefined command has been entered by the user.
 */
public class InvalidCommandException extends BobException {
    private static final String MESSAGE = "what";

    /**
     * Returns an error indicating that an invalid command has been given by the user.
     */
    public InvalidCommandException() {
        super(MESSAGE);
    }
}
