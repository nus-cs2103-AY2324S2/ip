package duke.exceptions.storage;

import java.io.IOException;

import duke.exceptions.DukeException;

public class SaveTasksFailedException extends DukeException {
    public SaveTasksFailedException(IOException ioException) {
        super(String.format("Failed to save tasks. %s", ioException.getMessage()));
    }
}
