package iggly.duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import iggly.model.Task;

/**
 * The {@link Storage} class is responsible for handling the reading, writing and saving task data to a file.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a {@link Storage} instance with the specified file path.
     *
     * @param filePath The file path where task data is stored or will be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Checks if the file associated with this storage exists.
     *
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    public boolean isExistingFile() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error checking file existence: " + e);
            return false;
        }
    }

    /**
     * Creates a new file at the specified file path.
     */
    public void createNewFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new file.");
        }
    }

    /**
     * Updates the file with the provided task list.
     *
     * @param taskList The list of tasks to be written to the file.
     */
    public void updateFile(ArrayList<Task> taskList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file and returns the task list.
     *
     * @return The list of tasks loaded from the file. If an error occurs during loading,
     *         an empty list is returned.
     */
    public ArrayList<Task> load() {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            @SuppressWarnings("unchecked")
            ArrayList<Task> taskList = (ArrayList<Task>) objectInputStream.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
