package duke.storage;

import duke.DukeException;

/**
 * Represents an exception that occurs when the storage file cannot be loaded.
 */
public class StorageLoadException extends DukeException {
    public StorageLoadException(String message, String botMessage) {
        super(message, botMessage);
    }
}
