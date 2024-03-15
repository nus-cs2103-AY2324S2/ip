package asher.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkDoneAndUndone() {
        Task task = new Task("Task");
        assertFalse(task.isDone);

        task.markDone();
        assertTrue(task.isDone);

        task.markUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testConstructor() {
        Task task = new Task("Go for a run");
        assertEquals("Go for a run", task.description);
        assertFalse(task.isDone);
    }
}
