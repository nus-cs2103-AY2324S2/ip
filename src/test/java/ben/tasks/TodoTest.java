package ben.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testSaveTask() {
        Todo todo = new Todo(false, "Buy groceries");
        assertEquals("T | 0 | Buy groceries", todo.saveTask());
    }

    @Test
    public void testToStringIncompleteTodo() {
        Todo todo = new Todo(false, "Clean the house");
        assertEquals("[T][ ] Clean the house", todo.toString());
    }

    @Test
    public void testToStringCompletedTodo() {
        Todo todo = new Todo(true, "Read a book");
        assertEquals("[T][X] Read a book", todo.toString());
    }

    @Test
    public void testSaveTaskCompletedTodo() {
        Todo todo = new Todo(true, "Exercise");
        assertEquals("T | 1 | Exercise", todo.saveTask());
    }
}
