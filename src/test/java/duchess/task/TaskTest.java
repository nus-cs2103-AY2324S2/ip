package duchess.task;

import duchess.tasks.Task;
import duchess.tasks.Deadline;
import duchess.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    // Test that a new task created should be unmarked
    @Test
    public void addUnmarkedDeadline() {
        Task t = new Deadline("toDoThis", "12/12/2024");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void markEventAsDone() {
        Task e = new Event("Birthday", "12/02/2024", "13/02/2024");
        e.markAsDone();
        assertEquals("X", e.getStatusIcon());
    }
}

