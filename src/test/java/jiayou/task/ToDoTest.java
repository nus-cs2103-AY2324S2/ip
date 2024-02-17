package jiayou.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


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

    @Test
    public void testEquals() {
        ToDo todo = new ToDo("Read a book");
        ToDo todoSame = new ToDo("Read a book");
        ToDo todoDiffDesc = new ToDo("Read a");
        assertEquals(true, todo.equals(todoSame));
        assertEquals(false, todo.equals(todoDiffDesc));
    }
}
