package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
