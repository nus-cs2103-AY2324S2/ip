package bozo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void testSave() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.save());
    }
}
