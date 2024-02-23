package ally.utils;

import ally.Ally;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Abstraction of Task Storage by Duke.
 * Stores Tasks into files
 */
public class Storage {
    protected String dataPath;

    /**
     * Creates a new Storage based on path
     * @param path String to path of storage file
     */
    public Storage(String path) {
        this.dataPath = path;
    }
    /**
     * Loads the serializable list of Tasks that have been previously stored.
     * Returns empty List if no previous entries found.
     * @return TaskList the list of tasks
     */
    public TaskList loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataPath))) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Saves Tasks into Storage
     */
    public void saveTasks() {
        try {
            // Ensure the parent directories exist
            File file = new File(dataPath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Save the tasks
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(Ally.lst);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
