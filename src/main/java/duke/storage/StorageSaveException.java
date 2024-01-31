package duke.storage;

import duke.DukeException;

/**
 * Represents an exception that occurs when the storage file cannot be saved.
 */
public class StorageSaveException extends DukeException {
    public StorageSaveException(String message, String botMessage) {
        super(message, botMessage);
    }
}
