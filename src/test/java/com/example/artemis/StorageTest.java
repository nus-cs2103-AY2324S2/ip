package com.example.artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void testLoad() {
        // Arrange
        String testFilePath = "./data/test.txt";
        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("testing"));

        try {
            // Save tasks to the test file
            storage.save(tasks.getTasks());

            // Act
            ArrayList<Task> loadedTasks = storage.load();

            // Assert
            assertEquals(tasks.getTasks().size(), loadedTasks.size());
            assertEquals(tasks.getTasks().get(0).toString(), loadedTasks.get(0).toString());

        } catch (ArtemisException e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Clean up: delete the test file
            deleteTestFile(testFilePath);
        }
    }

    @Test
    public void testSave() {
        // Arrange
        String testFilePath = "./data/test.txt";
        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("testing"));

        try {
            // Act
            storage.save(tasks.getTasks());

            // Assert
            assertTrue(new File(testFilePath).exists());

        } catch (ArtemisException e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Clean up: delete the test file
            deleteTestFile(testFilePath);
        }
    }

    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Failed to delete the test file: " + filePath);
            }
        }
    }
}
