package asher.Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class taskTest {
    @Test
    public void testMarkDoneAndUndone() {
        Task task = new Task("Task");
        assertFalse(task.isDone); // Task starts as not done

        task.markDone();
        assertTrue(task.isDone); // Task marked as done

        task.markUndone();
        assertFalse(task.isDone); // Task marked as undone again
    }

    @Test
    public void testConstructor() {
        Task task = new Task("Go for a run");
        assertEquals("Go for a run", task.description);
        assertFalse(task.isDone);
    }


}
