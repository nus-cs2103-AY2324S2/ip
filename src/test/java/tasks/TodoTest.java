package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    private final Task todo;
    private static final String TODO_DESCRIPTION = "borrow book";
    private static final String EXPECTED_FILE_STRING = "T|0|borrow book";
    private static final String EXPECTED_DISPLAY_STRING = "[T][ ] borrow book";

    public TodoTest() {
        this.todo = new Todo(TODO_DESCRIPTION);
    }

    @Test
    public void testConvertTaskToFileString() {
        String fileString = this.todo.convertTaskToFileString();
        assertEquals(EXPECTED_FILE_STRING, fileString);
    }

    @Test
    public void testToString() {
        String displayString = this.todo.toString();
        assertEquals(EXPECTED_DISPLAY_STRING, displayString);
    }
}
