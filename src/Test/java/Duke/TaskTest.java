package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test Task");
        task.markAsDone();
        assertEquals("[X] Test Task", task.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Task("Test Task");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals("[ ] Test Task", task.toString());
    }
}
