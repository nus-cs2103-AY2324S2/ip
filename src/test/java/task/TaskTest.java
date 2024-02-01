package task;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIcon_Undone() {
        assertEquals("[N] ", new Task("task").getStatusIcon());
    }
}
