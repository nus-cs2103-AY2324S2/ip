package dav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TodoTaskTest {

    @Test
    public void testToDataString() {
        TodoTask todoTask = new TodoTask("Test Todo");
        todoTask.addTag("testTag");
        assertEquals("T | 0 | Test Todo | testTag", todoTask.toDataString());
    }

    @Test
    public void testParseTaskValidData() {
        String data = "T | 1 | Test Todo | testTag1, testTag2";
        Task task = TodoTask.parseTask(data);

        String expectedOutput = "[T][X] Test Todo\nTags: testTag1, testTag2";
        assertEquals(expectedOutput, task.toString());
    }


    @Test
    public void testParseTaskInvalidData() {
        String invalidData = "Invalid Data";
        assertNull(TodoTask.parseTask(invalidData));
    }
}
