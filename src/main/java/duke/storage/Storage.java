package duke.storage;

import java.io.File;
import java.io.IOException;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TaskListParser;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws StorageLoadException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            TaskList data = TaskListParser.parse(file);
            return data;
        } catch (DukeException | IOException e) {
            throw new StorageLoadException("Failed to load data from file: " + filePath,
                    "Cannot load data from file: " + filePath + "\n" + "Sylvia will start with an empty task list.");
        }
    }

    public void save(TaskList list) throws StorageSaveException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            TaskListParser.writeToFile(list, file);
        } catch (IOException e) {
            throw new StorageSaveException("Failed to save data to file: " + filePath,
                    "Cannot save data to file: " + filePath);
        }
    }
}
