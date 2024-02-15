package bob.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getStorageFormat() {
        assertEquals("T | false | a", new Todo("a").getStorageFormat());

        Todo todo = new Todo("a");
        todo.setDone(true);
        assertEquals("T | true | a", todo.getStorageFormat());
    }

    @Test
    public void toString_notDone() {
        assertEquals("[T][ ] a", new Todo("a").toString());
    }

    @Test
    public void toString_done() {
        Todo todo = new Todo("a");
        todo.setDone(true);
        assertEquals("[T][X] a", todo.toString());
    }
}
