package dave;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import tasks.Todo;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskList_getTask_exceptionThrown() {
        try {
            new TaskList().getTask(1);
        } catch (Exception exc) {
            assertEquals("Index 1 out of bounds for length 0",
                        exc.getMessage());
        }
    }

    @Test
    public void taskList_getTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        assertEquals("[To-do][ ] read book", taskList.getTask(0).toString());
    }
}
