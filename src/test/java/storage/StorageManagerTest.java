package storage;

import tasklist.TaskList;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageManagerTest {

    private static final String TEST_DATA_DIR_PATH = "src/main/resources/data";
    private static final String TEST_TASK_SAVE_PATH = TEST_DATA_DIR_PATH + "/test.txt";

    private StorageManager storageManager = new StorageManager();

    @Test
    public void testSaveAndLoad() throws IOException {
        TaskList taskListToSave = new TaskList(new ArrayList<>());
        taskListToSave.add(new Todo("Task 1"));
        taskListToSave.add(new Todo("Task 2"));

        storageManager.save(taskListToSave);

        TaskList loadedTaskList = storageManager.load();

        // Check if loaded task list matches the original task list
        assertEquals(taskListToSave.getTasks().size(), loadedTaskList.getTasks().size());
        for (int i = 0; i < taskListToSave.getTasks().size(); i++) {
            assertEquals(taskListToSave.getTasks().get(i).getDescription(),
                         loadedTaskList.getTasks().get(i).getDescription());
        }
    }

    // @Test
    // public void testSaveNoExistingDirectory() throws IOException {
    //     File dataDir = new File(TEST_DATA_DIR_PATH);
    //     if (dataDir.exists()) {
    //         Files.delete(Paths.get(TEST_DATA_DIR_PATH));
    //     }

    //     TaskList taskListToSave = new TaskList(new ArrayList<>());
    //     taskListToSave.add(new Todo("Task 1"));
    //     taskListToSave.add(new Todo("Task 2"));

    //     storageManager.save(taskListToSave);

    //     assertTrue(Files.exists(Paths.get(TEST_TASK_SAVE_PATH)));
    // }
}
