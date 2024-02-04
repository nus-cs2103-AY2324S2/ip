package duke;

import duke.task.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Storage class used in Duke.
 */
class StorageTest {
    @TempDir
    static Path tempDir;
    static File tempFile;

    /**
     * Set up before Testing
     */
    @BeforeAll
    static void setup() {
        // Set a temporary file for testing
        tempFile = tempDir.resolve("duke.txt").toFile();
        Storage.dataPath = tempFile.getPath();
        // Initialize Duke.lst or the list used in your Storage class
        Duke.lst = new TaskList();
    }

    /**
     * Tests Saving and Loading Tasks
     * Creates Storage and check if tasks are loaded correctly
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void testSaveAndLoadTasks() throws IOException, ClassNotFoundException {
        // Add a task to the list
        Duke.lst.add(new Todo("Test task"));

        // Save the list to the file
        Storage.saveTasks();

        // Check if file was created
        assertTrue(tempFile.exists(), "File should be created after saving tasks.");

        // Load the list from the file
        TaskList loadedList = Storage.loadTasks();

        // Check if the loaded list contains the expected task
        assertEquals(1, loadedList.size(), "Loaded list should contain one task.");
        assertEquals("[T][ ] Test task", loadedList.get(0).toString(), "The description of the loaded task should match.");
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
