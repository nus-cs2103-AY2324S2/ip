package duke.storage;

import duke.DukeException;

public class StorageLoadException extends DukeException {
    public StorageLoadException(String message, String botMessage) {
        super(message, botMessage);
    }
}
