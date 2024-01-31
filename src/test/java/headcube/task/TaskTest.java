package headcube.task;
import headcube.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        assertEquals("[ ] hi", new Task("hi").toString());
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("hi");
        task.done();
        assertEquals("X",task.getStatusIcon());
    }
}
