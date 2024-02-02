package jiayou.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Complete homework");
        String expected = "[T][ ] Complete homework";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToStringForStore() {
        ToDo todo = new ToDo("Read a book");
        String expected = "T | 0 | Read a book";
        assertEquals(expected, todo.toStringForStore());
    }

    @Test
    public void testToDoDescription() {
        String description = "Go for a run";
        ToDo todo = new ToDo(description);
        assertEquals(description, todo.getDescription());
    }
}
