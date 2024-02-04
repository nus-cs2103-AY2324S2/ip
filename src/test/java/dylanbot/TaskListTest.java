package dylanbot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    /**
     * Tests if a TodoTask can be successfully created and added to the list of tasks
     */
    @Test
    public void createTodoTask_success() {
        TaskList tl = new TaskList(new ArrayList<>(), new Ui());
        Task testTask = new TodoTask("testing");
        tl.createTodo("testing");
        assertEquals(tl.getSize(), 1);

        assertEquals(testTask.getType(), tl.getTask(1).getType());
        assertEquals(testTask.getDesc(), tl.getTask(1).getDesc());
        assertEquals(testTask.isCompleted(), tl.getTask(1).isCompleted());
    }

    /**
     * Tests if a Task in the list of tasks can be successfully marked as completed
     */
    @Test
    public void markTask_success() {
        TaskList tl = new TaskList(new ArrayList<>(), new Ui());
        tl.createTodo("testing");
        assertFalse(tl.getTask(1).isCompleted());
        tl.mark(1);
        assertTrue(tl.getTask(1).isCompleted());
    }

}
