package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Read book");
        assertEquals("Read book", todo.getDescription());
        assertFalse(todo.checkDone());
    }

    @Test
    public void testMarkTodo() {
        Todo todo = new Todo("Read book");
        todo.setMark();
        assertTrue(todo.checkDone());
    }
}
