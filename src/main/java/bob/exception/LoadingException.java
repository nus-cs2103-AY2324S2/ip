package bob.exception;

/**
 * Represents an error occurred during the loading of tasks from storage.
 * A <code>LoadingException</code> object corresponds to an error arising from loading the tasks stored in storage.
 */
public class LoadingException extends BobException {
    /**
     * Returns an error occurred during the loading of tasks from storage.
     *
     * @param message The message for the error occurred during the loading of tasks from storage.
     */
    public LoadingException(String message) {
        super(message);
    }
}
