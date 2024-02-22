package jade.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jade.data.Task;
import jade.data.TaskList;
import jade.data.Todo;
import jade.exception.JadeException;

/**
 * The <code>StorageTest</code> class contains unit tests for the <code>Storage</code> class,
 * which is responsible for managing the storage of user tasks in a local file.
 * These tests ensure the proper functioning of the storage operations, including loading tasks
 * from a file, saving changes to a file, and handling task additions.
 * The above comment and StorageTest Class are generated using ChatGPT 3.5 using the prompts:
 * "generate JUnit tests for the following class", and
 * "generate a class block comment for the StorageTest class:{code}".
 * Modified by author for higher quality.
 */
public class StorageTest {
    private static final String TEST_FILE_PATH = "data/jadeTestList.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    public void load_emptyFile_returnsEmptyList() throws JadeException {
        try {
            File file = new File(TEST_FILE_PATH);
            file.delete();
            file.createNewFile();

            List<Task> tasks = storage.load();

            assertNotNull(tasks);
            assertEquals(0, tasks.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void saveChange_validTaskList_fileIsUpdated() throws JadeException, IOException {
        try {
            File file = new File(TEST_FILE_PATH);
            file.delete();
            file.createNewFile();

            TaskList<Task> taskList = new TaskList<>();
            Task task = new Todo("Test Task", false);
            taskList.add(task);

            storage.saveChange(taskList);

            assertTrue(file.exists());

            List<Task> loadedTasks = storage.load();
            assertNotNull(loadedTasks);
            assertEquals(1, loadedTasks.size());
            assertEquals(task.toString(), loadedTasks.get(0).toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
