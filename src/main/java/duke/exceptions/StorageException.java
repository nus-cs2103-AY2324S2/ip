package duke.exceptions;

public class StorageException extends Exception {
    public StorageException() {
        super("OOPS!!! There was an error with the Storage.");
    }

    public StorageException(String message) {
        super(message);
    }
}
