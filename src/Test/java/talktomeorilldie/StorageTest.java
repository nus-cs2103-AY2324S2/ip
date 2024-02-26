package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Storage class.
 */
public class StorageTest {

    /**
     * Tests the loadTasks method of Storage.
     */
    @Test
    public void testLoadTasks() {
        String filePath = "test.txt";
        Storage storage = new Storage(filePath);

        try {
            Task[] loadedTasks = storage.loadTasks();
            assertEquals(100, loadedTasks.length);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
