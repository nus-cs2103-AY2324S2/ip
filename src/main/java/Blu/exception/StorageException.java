package blu.exception;

/**
 * Represents exceptions caused by from storage operations in the Blu application.
 */
public class StorageException extends BluException {

    /**
     * Constructs a new StorageException with the specified detail message.
     * The detail message is prefixed with "Task Storage Exception:" to indicate the nature of the error.
     *
     * @param message The detail message which provides more information about the storage issue.
     */
    public StorageException(String message) {
        super("Task Storage Exception: " + message);
    }
}
