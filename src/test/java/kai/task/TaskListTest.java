package kai.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        List<Task> expectedList = new ArrayList<>();
        expectedList.add(task);

        assertEquals(expectedList.get(0), taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Sample Task");
        taskList.addTask(task);

        // Deleting a task with an invalid index should fail
        try {
            taskList.deleteTask(2);
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
}
