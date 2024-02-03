package duke.exceptions;

/**
 * The StorageException class represents an exception that occurs during storage operations in the Duke application.
 * It is a subclass of the standard Java Exception class.
 */
public class StorageException extends Exception {

    /**
     * Constructs a new StorageException with a default error message.
     * The default error message indicates that there was an error with the storage.
     */
    public StorageException() {
        super("OOPS!!! There was an error with the Storage.");
    }

    /**
     * Constructs a new StorageException with the specified error message.
     *
     * @param message The error message describing the specific cause of the exception.
     */
    public StorageException(String message) {
        super(message);
    }
}
