package lamball.exception;

/**
 * Storage exception class for file read/write-related issues
 */
public class StorageException extends Exception {

    /**
     * Constructor for Storage exception
     *
     * @param msg Message to show user.
     */
    public StorageException(String msg) {
        super(msg);
    }
}
