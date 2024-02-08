package dav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TodoTaskTest {

    @Test
    public void testToDataString() {
        TodoTask todoTask = new TodoTask("Test Todo");
        assertEquals("T | 0 | Test Todo", todoTask.toDataString());
    }

    @Test
    public void testParseTaskValidData() {
        String data = "T | 1 | Test Todo";
        Task task = TodoTask.parseTask(data);

        assertEquals("[T][X] Test Todo", task.toString());
    }

    @Test
    public void testParseTaskInvalidData() {
        // Invalid data should return null
        String invalidData = "Invalid Data";
        assertNull(TodoTask.parseTask(invalidData));
    }
}
