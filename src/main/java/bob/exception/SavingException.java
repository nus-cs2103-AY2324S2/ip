package bob.exception;

/**
 * Represents an error occurred during the saving of tasks to storage.
 * A <code>SavingException</code> object corresponds to an error arising from saving the tasks to storage.
 */
public class SavingException extends BobException {
    private static final String MESSAGE = "i cant save this task because %s";

    /**
     * Returns an error occurred during the saving of tasks to storage.
     *
     * @param message The message for the error occurred during the saving of tasks to storage.
     */
    public SavingException(String message) {
        super(String.format(MESSAGE, message));
    }
}
