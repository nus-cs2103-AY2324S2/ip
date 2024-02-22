package ally.utils;

import ally.ui.Ally;

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
    protected static String dataPath = "./data/duke.txt";

    /**
     * Loads the serializable list of Tasks that have been previously stored.
     * Returns empty List if no previous entries found.
     * @return TaskList the list of tasks
     */
    public static TaskList loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataPath))) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            //
        }
        return new TaskList();
    }

    /**
     * Save Tasks into Storage
     */
    public static void saveTasks() {
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
