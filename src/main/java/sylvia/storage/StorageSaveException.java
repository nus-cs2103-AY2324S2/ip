package sylvia.storage;

import sylvia.SylviaException;

/**
 * Represents an exception that occurs when the storage file cannot be saved.
 */
public class StorageSaveException extends SylviaException {
    public StorageSaveException(String message, String botMessage) {
        super(message, botMessage);
    }
}
