package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test cases for ToDo class.
 */
public class ToDoTest {
    // Test cases suggested by Github Copilot
    /**
     * Test case for toString method with normal string.
     */
    @Test
    public void toString_normalString_success() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }
    /**
     * Test case for toString method with spaces in string.
     */
    @Test
    public void toString_stringWithSpaces_success() {
        ToDo todo = new ToDo("test test");
        assertEquals("[T][ ] test test", todo.toString());
    }
    /**
     * Test case for toString method with done task.
     */
    @Test
    public void toString_markedAsDone_success() {
        ToDo todo = new ToDo("test");
        todo.setStatus(Task.Status.Done);
        assertEquals("[T][X] test", todo.toString());
    }
}
