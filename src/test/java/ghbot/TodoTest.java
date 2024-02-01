package ghbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testUnmarkToFile() {
        Todo todo = new Todo("sleep");
        assertEquals("T | 0 | sleep", todo.toFile());
    }

    @Test
    public void testMarkToFile() {
        Todo todo = new Todo("sleep");
        todo.isCompleted();
        assertEquals("T | 1 | sleep", todo.toFile());
    }

    @Test
    public void testUnmarkToString() {
        Todo todo = new Todo("sleep");
        assertEquals("[T][ ] sleep", todo.toString());
    }

    @Test
    public void testMarkToString() {
        Todo todo = new Todo("sleep");
        todo.isCompleted();
        assertEquals("[T][X] sleep", todo.toString());
    }
}
