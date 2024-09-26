package duke.mainUtils;

import duke.exceptions.InvalidDateException;
import duke.exceptions.StorageException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private Storage storage;
    private TaskList taskList;
    private final String testFilePath = "test_tasks.txt";

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        storage = new Storage(testFilePath, taskList);
    }

    @Test
    void testLoad() throws StorageException, InvalidDateException {
        // Create a test file with some sample data
        createTestFile();

        // Load tasks from the test file
        storage.load();

        // Assert that tasks are loaded correctly
        assertEquals(2, taskList.size()); // Assuming there are 2 tasks in the test file
    }

    @Test
    void testSave() throws StorageException, InvalidDateException {
        // Add some tasks to the task list
        taskList.add(new Task("Sample task 1"));
        taskList.add(new Task("Sample task 2"));

        // Save tasks to the test file
        storage.save();

        // Assert that the test file is created and contains the saved tasks
        File file = new File(testFilePath);
        assertTrue(file.exists()); // Check if the file exists
        // You may need to read the file content and check if it contains the expected tasks
    }

    private void createTestFile() throws StorageException {
        try (PrintWriter writer = new PrintWriter(new File(testFilePath))) {
            writer.println("T | 0 | Sample task 1");
            writer.println("T | 0 | Sample task 2");
        } catch (Exception e) {
            throw new StorageException( );
        }
    }
}