package eggy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
        assertEquals("[T][X] play games", new Todo("play games", true).toString());
        assertEquals("[T][ ] write essay", new Todo("write essay", false).toString());
    }

    @Test
    public void testFileStringConversion() {
        assertEquals("T | 0 | read book", new Todo("read book").toFileString());
        assertEquals("T | 1 | play games", new Todo("play games", true).toFileString());
        assertEquals("T | 0 | write essay", new Todo("write essay", false).toFileString());
    }
}
