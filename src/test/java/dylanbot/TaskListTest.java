package dylanbot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void createTodoTask_success() throws DylanBotException {
        TaskList tl = new TaskList(new ArrayList<>(), new Ui());
        Task testTask = new TodoTask("testing");
        tl.createTodo("testing");
        assertEquals(tl.getSize(), 1);

        assertEquals(testTask.getType(), tl.getTask(1).getType());
        assertEquals(testTask.getDesc(), tl.getTask(1).getDesc());
        assertEquals(testTask.isCompleted(), tl.getTask(1).isCompleted());
    }

    @Test
    public void markTask_success() throws DylanBotException {
        TaskList tl = new TaskList(new ArrayList<>(), new Ui());
        tl.createTodo("testing");
        assertFalse(tl.getTask(1).isCompleted());
        tl.mark(1);
        assertTrue(tl.getTask(1).isCompleted());
    }

}
