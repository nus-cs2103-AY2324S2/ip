package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_unMarkedTodo_success() {
        Todo todo = new Todo("study for finals");
        assertEquals("[T][ ] study for finals", todo.toString());
    }

    @Test
    public void toString_markedTodo_success() {
        Todo todo = new Todo("study for finals");
        todo.setDone(true);
        assertEquals("[T][X] study for finals", todo.toString());
    }

    @Test
    public void toFileString_unMarkedTodo_success() {
        Todo todo = new Todo("study for finals");
        assertEquals("T|0|study for finals", todo.toFileString());
    }

    @Test
    public void toFileString_markedTodo_success() {
        Todo todo = new Todo("study for finals");
        todo.setDone(true);
        assertEquals("T|1|study for finals", todo.toFileString());
    }
}
