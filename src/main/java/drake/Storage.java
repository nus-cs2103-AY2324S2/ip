package drake;
import java.io.*;
import java.util.ArrayList;

import drake.task.Task;

/**
 * Handles storage operations for tasks, such as saving to and loading from a file.
 */
public class Storage {
    private final String FILE_PATH;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    /**
     * Saves the list of tasks to the file.
     * 
     * @param tasks The ArrayList of Task objects to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving list!: " + e.getMessage());
        }
    }

    /**
     * Loads and returns the list of tasks from the file.
     * If the file does not exist, returns an empty list.
     * If the file exists but an error occurs during reading, also returns an empty list.
     * 
     * @return The ArrayList of Task objects loaded from the file.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (ArrayList<Task>) ois.readObject();
            } catch (IOException e) {
                return new ArrayList<>();
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }
}
