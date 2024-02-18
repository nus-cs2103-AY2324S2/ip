package storage;

import java.io.IOException;

import task.TaskList;

/**
 * Stub implementation of the Storage class for testing purposes.
 */
public class StorageStub extends Storage {

    private boolean isIoExceptionCalled;
    private boolean isAppendToFileCalled;
    private TaskList taskListToLoad;

    /**
     * Constructs a StorageStub with the specified TaskList to load.
     *
     * @param taskListToLoad the TaskList to be loaded in the stub implementation
     */
    public StorageStub(TaskList taskListToLoad) {
        super("stubFilePath");
        this.taskListToLoad = taskListToLoad;
    }

    /**
     * Constructs a StorageStub with the specified TaskList to load and a flag to simulate IOException.
     *
     * @param taskListToLoad        the TaskList to be loaded in the stub implementation
     * @param simulateIoException   flag to simulate IOException during file operations
     */
    public StorageStub(TaskList taskListToLoad, boolean simulateIoException) {
        super("stubFilePath");
        this.taskListToLoad = taskListToLoad;
        this.isIoExceptionCalled = simulateIoException;
    }

    @Override
    public void appendToFile(TaskList tasks) throws IOException {
        this.isAppendToFileCalled = true;
        if (isIoExceptionCalled) {
            throw new IOException("Simulated IOException");
        }
        // For simplicity, do nothing in the stub implementation
    }

    @Override
    public TaskList loadFile() {
        return taskListToLoad;
    }

    public boolean wasAppendToFileCalled() {
        return isAppendToFileCalled;
    }
}
