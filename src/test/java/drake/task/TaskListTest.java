package drake.task;

import org.junit.jupiter.api.Test;

import drake.Ui;
import drake.task.TaskList;
import drake.task.Task;
import drake.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTaskSucessTest() throws Exception {
        TaskList input = new TaskList();
        Task task1 = new Todo("sleep");
        input.addTask(task1);
        input.addTask(new Todo("eat"));

        Task actualOutput = input.getTask(0);
        
        assertEquals(task1, actualOutput);
    }

    @Test
    public void getTaskOutofBoundsTest() throws Exception {
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
    }
}