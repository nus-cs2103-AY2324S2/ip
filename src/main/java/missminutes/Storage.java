package missminutes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * Storage class to store and persist the TaskList class in disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates the storage object with the given file path.
     *
     * @param filePath The file path that the `TaskList` object will be stored to / loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the `TaskList` object to the specified file path. Saves as binary object.
     *
     * @param taskList The `TaskList` object to be persisted as a binary file
     * @throws MissMinutesException If unable to create the file to save at the specified path
     */
    public void saveTasks(TaskList taskList) throws MissMinutesException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException err) {
            throw new MissMinutesException("Failed to save to storage to " + filePath + " : " + err.getMessage());
        }
    }

    /**
     * Loads the `TaskList` object from the specified file path in disk.
     *
     * @return The `TaskList` object loaded from disk
     * @throws MissMinutesException If unable to load file from specified directory, or if class definition changed
     */
    public TaskList loadTasks() throws MissMinutesException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream out = new ObjectInputStream(fileIn);

            TaskList tasks = (TaskList) out.readObject();

            out.close();
            fileIn.close();
            return tasks;
        } catch (Exception err) { // Not just IOException and ClassNotFound, as MissMinutes.Task definition might've changed
            throw new MissMinutesException("Failed to load storage from " + filePath);
        }
    }
}
