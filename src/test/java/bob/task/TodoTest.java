package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
