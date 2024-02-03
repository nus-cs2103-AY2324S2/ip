package duke.exceptions.storage;

import java.io.IOException;

import duke.exceptions.DukeException;

public class LoadTasksFailedException extends DukeException {
    public LoadTasksFailedException(IOException ioException) {
        super(String.format("Failed to load tasks. %s", ioException.getMessage()));
    }
}
