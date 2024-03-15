package joy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * This class contains JUnit test cases to verify the functionality of the Task class.
 */
public class TaskTest {

    @Test
    public void taskInitializationTest() {
        Task task = new Task("Test Task");
        assertEquals("Test Task", task.description);
        assertFalse(task.isDone);
    }

    @Test
    public void getStatusIconTest() {
        Task task = new Task("Test Task");
        assertFalse(task.isDone);
        assertEquals(" ", task.getStatusIcon());

        task.setStatus();
        assertTrue(task.isDone);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void setStatusTest() {
        Task task = new Task("Test Task");
        assertFalse(task.isDone);

        task.setStatus();
        assertTrue(task.isDone);

        task.setStatus();
        assertFalse(task.isDone);
    }

    @Test
    public void toStringTest() {
        Task task = new Task("Test Task");
        assertEquals("[ ] Test Task", task.toString());

        task.setStatus();
        assertEquals("[X] Test Task", task.toString());
    }

    @Test
    public void numOfTasksTest() {
        // Ensure that the number of tasks is correctly tracked
        int x = Task.getNumOfTasks();

        Task task1 = new Task("Task 1");
        assertEquals(x + 1, Task.getNumOfTasks());

        Task task2 = new Task("Task 2");
        assertEquals(x + 2, Task.getNumOfTasks());

        // Ensure that decrementTotal reduces the count
        Task.decrementTotal();
        assertEquals(x + 1, Task.getNumOfTasks());
    }

    // Additional test for toFileString method
    @Test
    public void toFileStringTest() {
        Task task = new Task("Test Task");
        assertEquals("", task.toFileString());
    }
}
