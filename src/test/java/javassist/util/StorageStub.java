package javassist.util;

import java.io.IOException;
import java.util.ArrayList;

import javassist.exception.JavAssistException;
import javassist.task.Task;

/**
 * Represents a stub class for Storage.
 */
public class StorageStub extends Storage {
    public StorageStub(String file) throws IOException {
        super(file, file);
    }

    @Override
    public void writeToFile(TaskList list) {
        return;
    }

    @Override
    public ArrayList<Task> readFromFile() throws JavAssistException {
        return null;
    }
}
