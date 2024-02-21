package storage;

import tasks.Task;
import tasks.TaskSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StorageManager {
    private String taskSaveLocation;

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

    public void saveTasksToStorage(List<Task> tasks) {
        String tasksSerialized = TaskSerializer.serialize(tasks);
        try (FileWriter writer = new FileWriter(this.taskSaveLocation)) {
            writer.write(tasksSerialized);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasksFromStorage() {
        File dataFile = new File(this.taskSaveLocation);
        List<Task> tasks = new ArrayList<>();

        if (!dataFile.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(dataFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tasks.add(TaskSerializer.parseText(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
