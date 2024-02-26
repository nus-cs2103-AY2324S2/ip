package exceptions;

/**
 * Signals an error caused by IOException thrown during Storage operations.
 */
public class StorageException extends DukeException {

    public StorageException() {
        super();
    }
    public StorageException(String message) {
        super(message);
    }
}
