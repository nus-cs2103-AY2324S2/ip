package duke.util;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a stub class for Storage.
 */
public class StorageStub extends Storage {
    public StorageStub(String file, String parent) {
        super(file, parent);
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
