package friday.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import friday.task.TaskList;
import friday.task.Todo;

public class FridayTest {
    @Test
    public void testAddTodo() {
        TaskList taskList = new TaskList();
        taskList.addTodo("todo Testing Todo");
        Todo t = new Todo("Testing Todo");

        assertEquals(1, taskList.getLength());
        assertEquals(t.toString(), taskList.getTask(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();

        taskList.addTodo("todo Testing Delete");
        taskList.deleteTask(0);

        assertEquals(0, taskList.getLength());
    }
}
