package kai.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void taskConstructor_getIsDone_success() throws Exception {
        // first constructor creates a task object with isDone set to false
        assertEquals(false, new Task("read book").getIsDone());

        // first constructor creates a task object with isDone set to true
        assertEquals(true, new Task("read book", true).getIsDone());
    }

    @Test
    public void testOutputStringConversion() {
        assertEquals("O" + " | " + "read book", new Task("read book", true).formatStringForSaveFile());
    }
}
