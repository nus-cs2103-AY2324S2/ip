package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringify_(){
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.stringify());

        ToDo markedTodo = new ToDo("play", true);
        assertEquals("[T][X] play", markedTodo.stringify());
    }

    @Test
    public void testToString(){
        ToDo todo = new ToDo("read book");
        assertEquals("T | O | read book", todo.toString());

        ToDo markedTodo = new ToDo("play", true);
        assertEquals("T | X | play", markedTodo.toString());
    }
}
