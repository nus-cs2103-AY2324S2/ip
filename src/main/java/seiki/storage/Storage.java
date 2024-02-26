package seiki.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;

/**
 * Storage for the task list.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     * @throws SeikiException if there were errors reading and/or converting data from file.
     */
    public TaskList load() throws SeikiException {

        try {
            File file = new File(filePath);
            File parentFolder = file.getParentFile();

            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                return new TaskList();
            } else {
                return TaskListDecoder.decodeTaskList(Files.readAllLines(Path.of(filePath)));
            }
        } catch (FileNotFoundException e) {
            throw new SeikiException("A non-existent file scenario is already handled earlier.");
        } catch (IOException e) {
            throw new SeikiException("Error writing to file: " + filePath);
        }
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     * @param taskList
     * @throws SeikiException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList taskList) throws SeikiException {
        try {
            ArrayList<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(Path.of(filePath), encodedTaskList);
        } catch (IOException e) {
            throw new SeikiException("Error writing to file: " + filePath);
        }
    }
}
