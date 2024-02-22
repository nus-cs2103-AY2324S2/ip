package shodan.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import shodan.tasks.Task;
import shodan.tasks.TaskSerializer;

/**
 * This class is responsible for saving and loading
 * the state of the chatbot.
 */
public class StorageManager {
    private String taskSaveLocation;

    /**
     * Instantiates a new StorageManager. It reads config variables
     * stored in resources/config.properties to retrieve the paths
     * for the save directory and the savefile. Additionally, it
     * automatically creates the save directory in the current path
     * if it doesn't exist.
     */
    public StorageManager() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        // Ensure that the shodan.storage folder exists. Otherwise,
        // some file writing operations might fail.
        File dataDir = new File(rb.getString("DATA_DIR_PATH"));
        if (!dataDir.exists()) {
            try {
                Files.createDirectory(dataDir.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        taskSaveLocation = rb.getString("TASK_SAVE_PATH");
        assert taskSaveLocation != null : "Save path should not be empty";
    }

    /**
     * Save a list of tasks to the filesystem.
     *
     * @param tasks the tasks to save.
     */
    public void saveTasks(List<Task> tasks) {
        assert taskSaveLocation != null : "Save path should not be empty";
        String tasksSerialized = TaskSerializer.serialize(tasks);
        try (FileWriter writer = new FileWriter(taskSaveLocation)) {
            writer.write(tasksSerialized);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the list of tasks from the filesystem.
     *
     * @return the list of tasks.
     */
    public List<Task> loadTasks() {
        assert taskSaveLocation != null : "Save path should not be empty";
        File saveFile = new File(taskSaveLocation);
        if (!saveFile.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            return TaskSerializer.parseText(reader.lines());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
