package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The TodoTest class contains unit tests for the Todo class.
 */
public class TodoTest {
    private static final String EXPECTED_DISPLAY_STRING = "[T][ ] borrow book";
    private static final String EXPECTED_FILE_STRING = "T|0|borrow book";
    private static final String TODO_DESCRIPTION = "borrow book";
    private final Task todo;

    /**
     * Constructs a TodoTest object and initializes the todo task.
     */
    public TodoTest() {
        this.todo = new Todo(TODO_DESCRIPTION);
    }

    /**
     * Tests the convertTaskToFileString method of the Todo class.
     */
    @Test
    public void testConvertTaskToFileString() {
        String fileString = this.todo.convertTaskToFileString();
        assertEquals(EXPECTED_FILE_STRING, fileString);
    }

    /**
     * Tests the toString method of the Todo class.
     */
    @Test
    public void testToString() {
        String displayString = this.todo.toString();
        assertEquals(EXPECTED_DISPLAY_STRING, displayString);
    }
}
