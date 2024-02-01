package shodan.storage;

import shodan.tasks.Task;
import shodan.tasks.TaskSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StorageManager {
    private String taskSaveLocation;

    public StorageManager() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        /* Ensure that the shodan.storage folder exists. Otherwise, some file writing operations might fail. */
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

    public void saveTasks(List<Task> tasks) {
        String tasksSerialized = TaskSerializer.serialize(tasks);
        try (FileWriter writer = new FileWriter(this.taskSaveLocation)) {
            writer.write(tasksSerialized);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.taskSaveLocation))) {
            return TaskSerializer.parseText(reader.lines());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
