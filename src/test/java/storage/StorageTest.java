package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import tasks.Task;
import tasks.TaskList;



public class StorageTest {

@Test
public void test_write_to_file() throws IOException {
        // Create a temporary file for testing
        Path tempFile=Files.createTempFile("test",".txt");

        // Create a TaskList with some tasks
        TaskList taskList=new TaskList();
        taskList.addTask(new Task("Task 1",false));
        taskList.addTask(new Task("Task 2",true));
        taskList.addTask(new Task("Task 3",false));

        // Create a Storage object with the temporary file and the TaskList
        Storage storage = new Storage(tempFile.toString());

        // Write the tasks to the file
        storage.writeToFile(taskList);

        // Read the contents of the file
        String fileContents = Files.readString(tempFile);

        // Verify that the file contents match the expected format
        assertEquals("[T][ ] Task 1\n[T][X] Task 2\n[T][ ] Task 3\n", fileContents);

        // Delete the temporary file
        Files.delete(tempFile);
        }
}