package task;

import detective.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIcon_Undone() {
        assertEquals("[Y]", new Task("task").getStatusIcon());
    }
}
