package storage;

// STATIC imports
import static org.junit.jupiter.api.Assertions.assertEquals;

// STANDARD_JAVA_PACKAGE imports
import java.io.IOException;
import java.util.ArrayList;

// SPECIAL_IMPORTS group
import org.junit.jupiter.api.Test;

import tasklist.TaskList;
import tasks.Todo;

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
        assertEquals(taskListToSave.getSize(), loadedTaskList.getSize());
        for (int i = 0; i < taskListToSave.getSize(); i++) {
            assertEquals(taskListToSave.getTask(i).getDescription(),
                         loadedTaskList.getTask(i).getDescription());
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
