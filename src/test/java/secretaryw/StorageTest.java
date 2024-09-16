package secretaryw;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadTasksFromFile() {
        // Create a sample task
        Task task = new Task(TaskType.TODO, "Sample Task");
        ArrayList<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(task);

        // Create a Storage object
        Storage storage = new Storage();

        // Save the sample task to file
        try {
            storage.saveTasksToFile(expectedTasks);
        } catch (Exception e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }

        // Load tasks from file
        ArrayList<Task> loadedTasks = null;
        try {
            loadedTasks = storage.loadTasksFromFile();
        } catch (Exception e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }

        // Assert that the loaded tasks match the expected tasks
        assertEquals(expectedTasks.size(), loadedTasks.size());
        for (int i = 0; i < expectedTasks.size(); i++) {
            Task expectedTask = expectedTasks.get(i);
            Task loadedTask = loadedTasks.get(i);
            assertEquals(expectedTask.getDescription(), loadedTask.getDescription());
            assertEquals(expectedTask.getType(), loadedTask.getType());
            assertEquals(expectedTask.isDone(), loadedTask.isDone());
        }
    }

    @Test
    public void testSaveTasksToFile() {
        // Create a sample task
        Task task = new Task(TaskType.TODO, "Sample Task");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);

        // Create a Storage object
        Storage storage = new Storage();

        // Save tasks to file
        try {
            storage.saveTasksToFile(tasks);
        } catch (Exception e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }

        // Load tasks from file
        ArrayList<Task> loadedTasks = null;
        try {
            loadedTasks = storage.loadTasksFromFile();
        } catch (Exception e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }

        // Assert that the loaded tasks match the saved tasks
        assertEquals(tasks.size(), loadedTasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            Task savedTask = tasks.get(i);
            Task loadedTask = loadedTasks.get(i);
            assertEquals(savedTask.getDescription(), loadedTask.getDescription());
            assertEquals(savedTask.getType(), loadedTask.getType());
            assertEquals(savedTask.isDone(), loadedTask.isDone());
        }
    }
}
