package duke.storage;

import duke.DukeException;

public class StorageSaveException extends DukeException {
    public StorageSaveException(String message, String botMessage) {
        super(message, botMessage);
    }
}
