package duke;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskTest {
    @Test
    public void testGetStatusIcon() {
        Task taskUndone = new Task("", 0);
        assertEquals(" ", taskUndone.getStatusIcon());

        Task taskDone = new Task("", 1);
        assertEquals("X", taskDone.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test Task", 0);
        task.markAsDone();
        assertEquals(1, task.getIsDone());
    }

    @Test
    public void testMarkAsUndone() {
        Task task = new Task("Test Task", 1);
        task.markAsUndone();
        assertEquals(0, task.getIsDone());
    }
}