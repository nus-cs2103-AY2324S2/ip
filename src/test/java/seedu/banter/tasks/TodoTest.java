package seedu.banter.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    void todoInitialization() {
        String description = "Buy groceries";
        Todo todo = new Todo(description);

        assertEquals(description, todo.getDescription(), "Description should be initialized correctly");
        assertFalse(todo.isDone(), "Todo should be initialized as undone");
    }

    @Test
    void todoInitializationWithDoneStatus() {
        String description = "Read a book";
        Todo todo = new Todo(description, true);

        assertEquals(description, todo.getDescription(), "Description should be initialized correctly");
        assertTrue(todo.isDone(), "Todo should be initialized as done");
    }

    @Test
    void todoInitializationWithUndoneStatus() {
        String description = "Read a book";
        Todo todo = new Todo(description, false);

        assertEquals(description, todo.getDescription(), "Description should be initialized correctly");
        assertFalse(todo.isDone(), "Todo should be initialized as undone");
    }

    @Test
    void todoToString() {
        String description = "Clean the house";
        Todo todo = new Todo(description);

        assertEquals("[T][ ] " + description, todo.toString(),
                "toString() should return the expected string representation");
    }

    @Test
    void todoToStringWithDoneStatus() {
        String description = "Write code";
        Todo todo = new Todo(description, true);

        assertEquals("[T][X] " + description, todo.toString(),
                "toString() should return the expected string representation");
    }

    @Test
    void todoToStringWithUndoneStatus() {
        String description = "Write code";
        Todo todo = new Todo(description, false);

        assertEquals("[T][ ] " + description, todo.toString(),
                "toString() should return the expected string representation");
    }

    @Test
    void todoGetTaskType() {
        Todo todo = new Todo("Walk the dog");

        assertEquals("T", todo.getTaskType(), "getTaskType() should return the expected task type");
    }

    @Test
    void todoGetDateTimePriority() {
        Todo todo = new Todo("Walk the dog");

        assertEquals(LocalDateTime.MAX, todo.getDateTimePriority(),
                "getDateTimePriority() should return the expected datetime priority");
    }
}
