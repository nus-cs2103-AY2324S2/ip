package seedu.klara.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] hello", new Task("hello").toString());
        Task task = new Task("hello");
        task.markAsDone();
        assertEquals("[X] hello", task.toString());
    }
}
