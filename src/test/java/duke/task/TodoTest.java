package duke.task;

import org.junit.jupiter.api.Test;
import xilef.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Buy groceries");
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testTodoToStringForFile() {
        Todo todo = new Todo("Buy groceries");
        String expected = "T | 0 | Buy groceries";
        assertEquals(expected, todo.toStringForFile());
    }
}