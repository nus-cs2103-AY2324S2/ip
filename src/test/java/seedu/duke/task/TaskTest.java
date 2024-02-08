package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] hello", new Task("hello").toString());
        Task task = new Task("hello");
        task.markAsDone();
        assertEquals("[X] hello", task.toString());
    }
}
