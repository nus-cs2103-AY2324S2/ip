package duke.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
   

    @Test
    void testMarkAsDone() {
        Task t = new Task("read book");
        t.markAsDone();
        assertTrue(t.isDone);
    }

    @Test
    void testMarkAsNotDone() {
        Task t = new Task("read book");
        t.markAsDone();
        t.markAsNotDone();
        assertFalse(t.isDone);
    }
}
