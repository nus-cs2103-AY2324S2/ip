package duke.exceptions.storage;

import java.io.IOException;

import duke.exceptions.DukeException;

public class CreateTasksFileFailedException extends DukeException {
    public CreateTasksFileFailedException(IOException ioException) {
        super(String.format("Failed to create tasks file. %s", ioException.getMessage()));
    }
}
