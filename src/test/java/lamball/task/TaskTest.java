package lamball.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {
    @Test
    public void taskTest() {
        Task testTask = new Task("TEST");
        assertEquals(testTask.command(), "How did you get here?");
    }

    @Test
    public void taskStringTest() {
        Task testTask = new Task("TEST");
        assertEquals(testTask.toString(), "[ ] TEST");
    }
}
