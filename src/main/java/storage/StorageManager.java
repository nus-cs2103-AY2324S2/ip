package storage;

import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import tasklist.TaskList;

/**
 * manages the storage of tasks to and from a file.
 * Contains methods to save and load tasks.
 */
public class StorageManager {
    protected String taskSaveLocation;

    /**
     * Constructs a StorageManager object.
     * creates the necessary directories and initializes the file path for saving tasks.
     */
    public StorageManager() {
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
        this.taskSaveLocation = rb.getString("TASK_SAVE_PATH");
    }

    /**
     * Saves the task list to the file system.
     *
     * @param tasklist The task list to be saved.
     */
    public void save(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        String serializedTask;
        for (int i = 0; i < tasks.getSize(); i ++) {
            serializedTask = TaskSerializer.serialize(tasks.getTask(i));
            sb.append(serializedTask); 
            sb.append("\n"); 
        }
        
        try (FileWriter writer = new FileWriter(this.taskSaveLocation)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file system
     *
     * @return The loaded task list.
     */

    public TaskList load() {
        File dataFile = new File(this.taskSaveLocation);
        List<Task> tasks = new ArrayList<>();

        if (!dataFile.exists()) {
            return new TaskList(tasks);
        }

        try (Scanner sc = new Scanner(dataFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task t = TaskSerializer.parseText(line);
                tasks.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TaskList(tasks);
    }
}
