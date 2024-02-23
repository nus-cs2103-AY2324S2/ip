package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringify() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.stringify());

        ToDo markedTodo = new ToDo("play");
        markedTodo.setStatus(true);
        assertEquals("[T][X] play", markedTodo.stringify());
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | O | read book | null", todo.toString());

        ToDo markedTodo = new ToDo("play");
        markedTodo.setStatus(true);
        assertEquals("T | X | play | null", markedTodo.toString());
    }
}
