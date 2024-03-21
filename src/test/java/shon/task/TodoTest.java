package shon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo1 = new Todo("Read book", false);
        assertEquals("[T][ ] Read book", todo1.toString());

        Todo todo2 = new Todo("Read book", true);
        assertEquals("[T][X] Read book", todo2.toString());
    }

    @Test
    public void testFormatData() {
        Todo todo1 = new Todo("Read book", false);
        assertEquals("T | 0 | Read book", todo1.formatData());

        Todo todo2 = new Todo("Read book", true);
        assertEquals("T | 1 | Read book", todo2.formatData());
    }
}
