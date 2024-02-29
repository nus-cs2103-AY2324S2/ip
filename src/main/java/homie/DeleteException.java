package homie;

/**
 * DeleteException thrown when no index is given, or index is out of range when deleting a task.
 */
public class DeleteException extends Exception {
    /**
     * Constructor for DeleteException class.
     *
     * @param message The error message.
     */
    public DeleteException(String message) {
        super("Bruh... " + message + "\nPlease follow the format: \ndelete {INDEX}");
    }
}
