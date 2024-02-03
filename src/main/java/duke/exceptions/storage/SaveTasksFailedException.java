package duke.exceptions.storage;

import duke.exceptions.DukeException;
import java.io.IOException;

public class SaveTasksFailedException extends DukeException {
    public SaveTasksFailedException(IOException ioException) {
        super(String.format("Failed to save tasks. %s", ioException.getMessage()));
    }
}
