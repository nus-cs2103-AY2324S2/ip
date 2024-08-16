package sylvia.storage;

import sylvia.SylviaException;

/**
 * Represents an exception that occurs when the storage file cannot be loaded.
 */
public class StorageLoadException extends SylviaException {
    public StorageLoadException(String message, String botMessage) {
        super(message, botMessage);
    }
}
