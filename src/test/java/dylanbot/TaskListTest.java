package dylanbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    /**
     * Tests if a TodoTask can be successfully created and added to the list of tasks
     */
    @Test
    public void createTodoTask_success() {
        TaskList tl = new TaskList(new ArrayList<>(), new HashMap<>(), new Ui());
        Task testTask = new TodoTask("testing");
        tl.createTodo("testing");
        assertEquals(tl.getSize(), 1);

        assertEquals(testTask.getType(), tl.getTask(1).getType());
        assertEquals(testTask.getDesc(), tl.getTask(1).getDesc());
        assertEquals(testTask.checkCompleted(), tl.getTask(1).checkCompleted());
    }

    /**
     * Tests if a Task in the list of tasks can be successfully marked as completed
     */
    @Test
    public void markTask_success() {
        TaskList tl = new TaskList(new ArrayList<>(), new HashMap<>(), new Ui());
        tl.createTodo("testing");
        assertFalse(tl.getTask(1).checkCompleted());
        tl.mark(1);
        assertTrue(tl.getTask(1).checkCompleted());
    }
}
