package storage;

import exceptions.FileError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;


public class StorageTest {

    private Storage storage;
    private final String testFile = "src/main/java/data/testTasks.txt";

    @BeforeEach
    public void setUpTestFile() {
        storage = new Storage(testFile);
    }

    @AfterEach
    public void deleteTestFile() {
        File file = new File(testFile);
        if (file.exists()) {
            boolean isDeleted = file.delete();
        }
    }

    @Test
    public void testWriteValidData() throws FileError, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Test Todo"));
        tasks.add(new Deadline("Test Deadline", "2024-02-05"));
        tasks.add(new Event("Test Event", "2024-02-05" , "2024-02-06"));

        storage.write(tasks);

        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Prepare the expected string representation of tasks
        StringBuilder expectedContent = new StringBuilder();
        for (Task task : tasks) {
            expectedContent.append(task.prepareForStorage()).append("\n");
        }

        // Assert that the file content matches the expected string
        assertEquals(expectedContent.toString().trim(), fileContent.toString().trim());

    }
}
