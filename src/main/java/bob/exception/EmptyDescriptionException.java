package bob.exception;

/**
 * Represents an error indicating that a command is given without a description.
 * An <code>EmptyDescriptionException</code> object corresponds to an error indicating that a command
 * that requires a description is given without a description.
 */
public class EmptyDescriptionException extends BobException {
    private static final String MESSAGE = "%s what";

    /**
     * Returns an error indicating that a command is given without a description.
     *
     * @param command The command that expects a description.
     */
    public EmptyDescriptionException(String command) {
        super(String.format(MESSAGE, command));
    }
}
