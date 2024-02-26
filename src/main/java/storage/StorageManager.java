package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import exceptions.CalException;
import tasklist.TaskList;
import tasks.Task;

/**
 * manages the storage of tasks to and from a file.
 * Contains methods to save and load tasks.
 */
public class StorageManager {
    public final static String TASK_SAVE_PATH = "TASK_SAVE_PATH";
    public final static String TEST_SAVE_PATH = "TEST_SAVE_PATH";

    protected String taskSaveLocation;
    /**
     * Constructs a StorageManager object.
     * creates the necessary directories and initializes the file path for saving tasks.
     */
    public StorageManager(String savePath) {
        // have to add src/main/resources folder the Java Source Code
        ResourceBundle rb = ResourceBundle.getBundle("config");
        File dataDir = new File(rb.getString("DATA_DIR_PATH"));

        if (!dataDir.exists()) {
            try {
                Files.createDirectory(dataDir.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.taskSaveLocation = rb.getString(savePath);
    }

    /**
     * Saves the task list to the file system.
     *
     * @param tasks The task list to be saved.
     * @throws CalException 
     */
    public void save(TaskList tasks) throws CalException {
        assert taskSaveLocation != null : "Save path should not be empty";
        StringBuilder sb = new StringBuilder();
        String serializedTask;
        for (int i = 0; i < tasks.getSize(); i++) {
            serializedTask = TaskSerializer.serialize(tasks.getTask(i));
            sb.append(serializedTask);
            sb.append("\n");
        }
        try (FileWriter writer = new FileWriter(this.taskSaveLocation)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new CalException("Failed to save to database.");
        }
    }

    /**
     * Loads tasks from the file system
     *
     * @return The loaded task list.
     * @throws CalException 
     */

    public TaskList load() throws CalException {
        assert taskSaveLocation != null : "Save path should not be empty";
        File dataFile = new File(this.taskSaveLocation);
        TaskList tasks = new TaskList(new ArrayList<>());

        if (!dataFile.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(dataFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task t = TaskSerializer.parseText(line);
                tasks.add(t);
            }
        } catch (IOException e) {
            throw new CalException("Failed to load database.");
        }

        return tasks;
    }
}
