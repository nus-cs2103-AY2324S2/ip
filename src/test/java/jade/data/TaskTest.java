package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * The <code>TaskTest</code> class contains unit tests for the <code>Task</code> class,
 * which represents a generic task within the task management system.
 * These tests verify various functionalities of the <code>Task</code> class, including
 * retrieving the status icon, formatting the status, converting the task to a string,
 * and checking if the task contains a specific keyword.
 * The above comment is generated using ChatGPT 3.5 using the prompt:
 * "generate a block comment for the TaskTest class:{code}".
 * Modified by author for higher quality.
 */
public class TaskTest {
    @Test
    public void getStatusIcon() {
        // task with done status will return "X"
        assertEquals("X", new Task("a", true).getStatusIcon());
        // task with undone status will return " "
        assertEquals(" ", new Task("a").getStatusIcon());
    }

    @Test
    public void statusFormatter() {
        assertEquals("1", new Task("a", true).statusFormatter());
        assertEquals("0", new Task("a").statusFormatter());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[ ] read a book", new Task("read a book").toString());
        assertEquals("[X] read two books", new Task("read two books", true).toString());
    }
    @Test
    public void containsKeyword() {
        assertTrue(new Task("read a book").containsKeyword("read"));
        assertFalse(new Task("read a book").containsKeyword("study"));
    }
}
