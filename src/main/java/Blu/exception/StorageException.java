package Blu.exception;

public class StorageException extends BluException {
    public StorageException(String message) {
        super("Task Storage Exception: " + message);
    }
    
}
