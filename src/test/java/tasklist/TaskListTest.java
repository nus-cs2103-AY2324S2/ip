package tasklist;

import exceptions.DuplicateInsertionException;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        // Create a TaskList
        TaskList taskList = new TaskList(new ArrayList<>());

        // Create tasks for testing
        Task task1 = new Todo("Task 1");
        Task task2 = new Deadline("Task 2", LocalDate.now());

        try {
            // Add the first task, expect success
            assertTrue(taskList.addTask(task1), "Adding the first task should succeed");
            assertEquals(1, taskList.getSize(), "TaskList size should be 1 after adding the first task");
            assertTrue(taskList.fetchAll().contains(task1), "TaskList should contain the first task");

            // Try to add the same task again, expect DuplicateInsertionException
            assertThrows(DuplicateInsertionException.class, () -> taskList.addTask(task1),
                    "Adding the same task should throw DuplicateInsertionException");

            // Add a different task, expect success
            assertTrue(taskList.addTask(task2), "Adding the second task should succeed");
            assertEquals(2, taskList.getSize(), "TaskList size should be 2 after adding the second task");
            assertTrue(taskList.fetchAll().contains(task2), "TaskList should contain the second task");

        } catch (DuplicateInsertionException e) {
            fail("Unexpected DuplicateInsertionException: " + e.getMessage());
        }
    }
}
