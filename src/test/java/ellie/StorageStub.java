package ellie;

import java.util.ArrayList;

import ellie.task.Task;


/**
 * A stub implementation of the {@link Storage} class for testing purposes.
 * This stub does not perform any actual file loading or saving operations.
 */
public class StorageStub extends Storage {

    /**
     * Constructs a {@code StorageStub} object with the specified file path and directory.
     *
     * @param filePath  The file path to use for storage.
     * @param directory The directory where the file is located.
     */
    public StorageStub(String filePath, String directory) {
        super("./data/storageStubTest.txt", "./data");
    }

    /**
     * {@inheritDoc}
     * Stub implementation for loading tasks from storage.
     * This method does nothing as it's a stub for testing purposes.
     */
    @Override
    public void load(ArrayList<Task> taskList) {
        // Stub implementation: Do nothing for loading
    }

    /**
     * {@inheritDoc}
     * Stub implementation for saving tasks to storage.
     * This method does nothing as it's a stub for testing purposes.
     */
    @Override
    public void save(ArrayList<Task> taskList) {
        // Stub implementation: Do nothing for saving
    }
}
