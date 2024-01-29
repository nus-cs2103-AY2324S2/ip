package storage;
import task.TaskList;

import java.io.IOException;

public class StorageStub extends Storage {

    private boolean simulateIOException;
    public boolean appendToFileCalled;
    private TaskList taskListToLoad;

    public StorageStub(TaskList taskListToLoad) {
        super("stubFilePath");
        this.taskListToLoad = taskListToLoad;
    }

    public StorageStub(TaskList taskListToLoad, boolean simulateIOException) {
        super("stubFilePath");
        this.taskListToLoad = taskListToLoad;
        this.simulateIOException = simulateIOException;
    }

    @Override
    public void appendToFile(TaskList tasks) throws IOException {
        appendToFileCalled = true;
        if (simulateIOException) {
            throw new IOException("Simulated IOException");
        }
        // For simplicity, do nothing in the stub implementation
    }

    @Override
    public TaskList loadFile() {
        return taskListToLoad;
    }

    public boolean wasAppendToFileCalled() {
        return appendToFileCalled;
    }
}
