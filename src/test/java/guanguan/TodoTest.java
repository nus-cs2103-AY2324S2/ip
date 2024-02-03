package guanguan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void saveToTextTest() {
        Todo todo = new Todo("Complete CS2103T");
        todo.markDone();

        assertEquals("T | 1 | Complete CS2103T", todo.saveToText());
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("Complete CS2103T");

        assertEquals("[T][ ] Complete CS2103T", todo.toString());
    }
}
