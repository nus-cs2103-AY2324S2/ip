package pyrite.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    // Test cases suggested by Github Copilot
    @Test
    public void toString_normalString_success() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }
    @Test
    public void toString_stringWithSpaces_success() {
        ToDo todo = new ToDo("test test");
        assertEquals("[T][ ] test test", todo.toString());
    }
    @Test
    public void toString_markedAsDone_success() {
        ToDo todo = new ToDo("test");
        todo.setStatus(Task.Status.DONE);
        assertEquals("[T][X] test", todo.toString());
    }
}
