package misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import irwyn.tasks.Task;

/**
 * This class encapsulates the StorageManager class.
 * The StorageManager helps to manage the loading and saving of tasks.
 *
 * @author Irwyn
 * @version Week-3
 */
public class StorageManager {
    private final String filePath;

    /**
     * Constructor for a StorageManager object.
     *
     * @param filePath The file path to save tasks to.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from storage.
     *
     * @return File that contains list of tasks.
     * @throws Exception If the file doesn't exist.
     */
    public File load() throws Exception {
        try {
            File data = new File(filePath);
            if (!data.exists()) {
                System.out.println("Data file not found. A new file will be created.\n");
                data.getParentFile().mkdirs();
                data.createNewFile();
            }
            return data;
        } catch (Exception e) {
            System.out.println("couldn't create the new file exception");
            throw new Exception();
        }
    }

    /**
     * Saves a list of tasks in storage.
     *
     * @param tasks An ArrayList of tasks to be converted to string form.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : tasks) {
                fw.write(t.toFileFormat()
                        + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}
