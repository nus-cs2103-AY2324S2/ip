package ellie;

import ellie.task.Task;
import java.util.ArrayList;

public class StorageStub extends Storage {

    public StorageStub(String filePath, String directory) {
        super("./data/storageStubTest.txt", "./data");
    }

    @Override
    public void load(ArrayList<Task> taskList) {
        // Stub implementation: Do nothing for loading
    }

    @Override
    public void save(ArrayList<Task> taskList) {
        // Stub implementation: Do nothing for saving
    }
}