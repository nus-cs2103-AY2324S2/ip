package duke.exceptions.storage;

import duke.exceptions.DukeException;
import java.io.IOException;

public class LoadTasksFailedException extends DukeException {
    public LoadTasksFailedException(IOException ioException) {
        super(String.format("Failed to load tasks. %s", ioException.getMessage()));
    }
}
