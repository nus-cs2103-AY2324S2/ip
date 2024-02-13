package duke.util;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a stub class for Storage.
 */
public class StorageStub extends Storage {
    public StorageStub(String file) throws IOException {
        super(file);
    }

    @Override
    public void writeToFile(TaskList list) {
        return;
    }

    @Override
    public ArrayList<Task> readFromFile() throws DukeException {
        return null;
    }
}
