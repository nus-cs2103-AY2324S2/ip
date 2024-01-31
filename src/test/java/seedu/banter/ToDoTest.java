package seedu.banter;

import org.junit.jupiter.api.Test;
import seedu.banter.tasks.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    @Test
    public void todoInitialization() {
        String description = "Buy groceries";
        Todo todo = new Todo(description);

        assertEquals(description, todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void todoInitializationWithDoneStatus() {
        String description = "Read a book";
        Todo todo = new Todo(description, true);

        assertEquals(description, todo.getDescription());
        assertTrue(todo.isDone());
    }
    
    @Test
    public void todoInitializationWithUndoneStatus() {
        String description = "Read a book";
        Todo todo = new Todo(description, false);

        assertEquals(description, todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void todoToString() {
        String description = "Clean the house";
        Todo todo = new Todo(description);

        assertEquals("[T][ ] " + description, todo.toString());
    }

    @Test
    public void todoToStringWithDoneStatus() {
        String description = "Write code";
        Todo todo = new Todo(description, true);

        assertEquals("[T][X] " + description, todo.toString());
    }
    
    @Test
    public void todoToStringWithUndoneStatus() {
        String description = "Write code";
        Todo todo = new Todo(description, false);

        assertEquals("[T][ ] " + description, todo.toString());
    }

    @Test
    public void todoGetTaskType() {
        Todo todo = new Todo("Walk the dog");

        assertEquals("T", todo.getTaskType());
    }
}
