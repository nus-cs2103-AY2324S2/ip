package duke.exceptions.storage;

import duke.exceptions.DukeException;
import java.io.IOException;

public class CreateTasksFileFailedException extends DukeException {
    public CreateTasksFileFailedException(IOException ioException) {
        super(String.format("Failed to create tasks file. %s", ioException.getMessage()));
    }
}
