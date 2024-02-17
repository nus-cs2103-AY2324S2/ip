package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import objects.TaskList;
import view.EncaseLines;
/**
 * The Storage class is responsible for handling the saving and loading of the TaskList to and from a file.
 * It provides methods to check if the file exists, save the TaskList to the file, and load the TaskList from the file.
 */
public class Storage {

    /** The file path where the TaskList is stored. */
    private static final String FILE_PATH = "data.txt";

    /**
     * Checks if the file containing the TaskList exists.
     *
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists() {
        File file = new File(FILE_PATH);

        return file.exists();
    }

    /**
     * Saves the provided TaskList to the file.
     *
     * @param taskList The TaskList to be saved.
     */
    public static void save(TaskList taskList) {
        try (FileOutputStream fileOutput = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(taskList);

        } catch (IOException e) {
            EncaseLines.display("Error saving task list: " + e.getMessage());

        }
    }

    /**
     * Loads the TaskList from the file. If the file does not exist, creates a new TaskList.
     *
     * @return The loaded TaskList or a new TaskList if the file doesn't exist.
     */
    public static TaskList load() {
        if (!fileExists()) {
            EncaseLines.display("Data not found, creating new file...");
            return new TaskList();
        }

        try (FileInputStream fileInput = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            TaskList tasks = (TaskList) objectInput.readObject();
            EncaseLines.display("Existing data found, loading...");

            // Perform null check here
            if (tasks == null) {
                EncaseLines.display("Error loading data: loaded task list is null");
                return new TaskList(); // or handle it according to your logic
            }

            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            EncaseLines.display("Error loading data: " + e.getMessage());
        }

        return new TaskList(); // Return a new TaskList in case of failure
    }
}
