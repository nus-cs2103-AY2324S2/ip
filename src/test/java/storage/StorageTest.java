package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Storage storage=new Storage(tempFile.toString(),taskList);

        // Write the tasks to the file
        storage.writeToFile();

        // Read the contents of the file
        String fileContents=Files.readString(tempFile);

        // Verify that the file contents match the expected format
        assertEquals("[T][ ] Task 1\n[T][X] Task 2\n[T][ ] Task 3\n",fileContents);

        // Delete the temporary file
        Files.delete(tempFile);
        }

@Test
public void test_generate_tasks()throws IOException{
        // Create a temporary file for testing
        Path tempFile=Files.createTempFile("test",".txt");

        // Write some tasks to the file
        Files.writeString(tempFile,"[T][ ] Task 1\n[D][X] Task 2\n[T][ ] Task 3\n");

        // Create a TaskList
        TaskList taskList=new TaskList();

        // Create a Storage object with the temporary file and the TaskList
        Storage storage=new Storage(tempFile.toString(),taskList);

        // Generate the tasks from the file
        String generatedTasks=storage.generateTasks();

        // Verify that the generated tasks match the expected format
        assertEquals("1. [T][ ] Task 1\n2. [D][X] Task 2\n3. [T][ ] Task 3\n",generatedTasks);

        // Delete the temporary file
        Files.delete(tempFile);
        }
}