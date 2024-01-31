package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TaskListParser;

/**
 * Represents a storage for loading and saving the task list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list loaded from the file.
     * @throws StorageLoadException If the task list fails to load.
     */
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

    /**
     * Saves the task list to the file.
     *
     * @param list The task list to be saved.
     * @throws StorageSaveException If the task list fails to save.
     */
    public void save(TaskList list) throws StorageSaveException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false);
            writer.write(TaskListParser.serialize(list));
            writer.close();
        } catch (IOException e) {
            throw new StorageSaveException("Failed to save data to file: " + filePath,
                    "Cannot save data to file: " + filePath);
        }
    }
}
