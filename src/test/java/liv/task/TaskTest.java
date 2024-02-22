package liv.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void markAsDoneTest() {
        Task task = new Task("Explore tP features");
        task.markAsDone();
        assertTrue(task.isDone);
    }
    @Test
    public void getDisplayedStringTest() {
        Task task = new Task("Prepare CS2101 CA1");
        String displayed = task.getDisplayedString();
        assertEquals(displayed, "[ ] Prepare CS2101 CA1");
    }
}
