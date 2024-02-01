package utilities;

import tasks.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The StorageStub class deals with loading tasks and saving tasks into a list
 * (it simulates saving and loading task from a text file for the ParserTest)
 */
public class StorageStub extends Storage {
    private List<Task> tasks;

    public StorageStub(String filePath) {
        super(filePath);
        this.tasks = new ArrayList<>();
    }

    @Override
    public List<Task> loadFromFile() {
        return this.tasks;
    }

    @Override
    public void writeToFile(List<Task> tasks) {
        this.tasks = tasks;
    }

}
