package ally;

import ally.task.Todo;
import ally.utils.Storage;
import ally.utils.TaskList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for Storage class used in Duke.
 */
class StorageTest {
    @TempDir
    static Path tempDir;
    static File tempFile;
    static Storage storage;
    static TaskList lst = new TaskList();

    /**
     * Set up before Testing
     */
    @BeforeAll
    static void setup() {
        // Set a temporary file for testing
        tempFile = tempDir.resolve("ally.txt").toFile();
        storage = new Storage(tempFile.getPath());
        // Initialize list used in your Storage class
        lst = new TaskList();
    }

    /**
     * Tests Saving and Loading Tasks
     * Creates Storage and check if tasks are loaded correctly
     *
     * @throws java.io.IOException no file found
     * @throws ClassNotFoundException incorrect objects found
     */
    @Test
    void testSaveAndLoadTasks() {
        // Add a task to the list
        lst.addToList(new Todo("Test task"));
        System.out.println(lst);

        // Save the list to the file
        storage.saveTasks();

        System.out.println(tempFile.exists());

        // Check if file was created
        assertTrue(tempFile.exists(), "File should be created after saving tasks.");
//
//        // Load the list from the file
        TaskList loadedList = storage.loadTasks();
        System.out.println(loadedList);
//
//        // Check if the loaded list contains the expected task
        assertEquals(1, loadedList.size(), "Loaded list should contain one task.");
//        assertEquals("[T][ ] Test task", loadedList.get(0).toString(), "The description of the loaded task should match.");
    }

    /**
     * Removes Temporary Files after testing
     */
    @AfterAll
    static void cleanup() {
        // Delete the temporary file if it exists
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
