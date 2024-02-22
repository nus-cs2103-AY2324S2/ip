package drake.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getTaskSuccessTest() {
        TaskList input = new TaskList();
        Task task1 = new Todo("sleep");
        input.addTask(task1);
        input.addTask(new Todo("eat"));

        Task actualOutput = input.getTask(0);
        assertEquals(task1, actualOutput);
    }

    @Test
    public void getTaskOutOfBoundsTest() {
        TaskList input = new TaskList();
        Task task1 = new Todo("sleep");
        input.addTask(task1);
        input.addTask(new Todo("eat"));

        try {
            Task actualOutput = input.getTask(3);
            assertEquals(task1, actualOutput);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Task index is out of bounds.", e.getMessage());
        }

        try {
            Task actualOutput = input.getTask(-1);
            assertEquals(task1, actualOutput);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Task index is out of bounds.", e.getMessage());
        }
    }
}
