package floofy.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ToDo class.
 */
public class ToDoTest {

    /**
     * Tests the string format of the todo task.
     */
    @Test
    public void ToDoStringFormat_validInput_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    /**
     * Tests the file format of the todo task.
     */
    @Test
    public void ToDoFileFormat_validInput_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
    }
}
