package kai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Handles the storage of tasks to and from a file.
 * Provides methods to save and load tasks from a specified file path.
 */
public class Storage {

    /**
     * The default file path for storing tasks.
     */
    private static final String FILE_PATH = "./data/kai.txt";

    /**
     * Constructs a storage instance with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
    }

    /**
     * Ensures the existence of the folder and file for storing tasks.
     * If the folder or file does not exist, it creates them.
     */
    private static void ensureFolderAndFileExists() {
        File folder = new File("./data");
        File file = new File(folder, "/kai.txt");

        if (!folder.exists()) {
            // Creates the directory named by this abstract pathname,
            // including any necessary but nonexistent parent directories
            folder.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                System.err.println("Error creating new file; " + e.getMessage());
            }
        }
    }

    /**
     * Saves the provided list of tasks to the specified file path.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        ensureFolderAndFileExists();
        // OOS writes primitive data types and graphs of Java objects to an OutputStream
        // Both FileInputStream and FileOutputStream create byte streams linked to files
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            // Open an OOS for writing to the files
            oos.writeObject(tasks);

        } catch (IOException e) {
            // Catch any IOException that might occur during the file writing process
            System.err.println("Error saving tasks:" + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the specified file path.
     *
     * @return The list of tasks loaded from the file, or null if an error occurs.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting with an empty task list");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks: Corrupted data file. Starting with an empty task list.");
        }

        return tasks;
    }

}
