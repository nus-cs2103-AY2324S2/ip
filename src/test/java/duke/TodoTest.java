package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoConstruction() {
        Todo todo = new Todo("Read book", false);
        assertEquals("Read book", todo.description);
        assertEquals(false, todo.isDone);
    }

    @Test
    public void testToFileFormat() {
        Todo todo = new Todo("Read book", true);
        String expectedFormat = "T | true | Read book";
        assertEquals(expectedFormat, todo.toFileFormat());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Read book", false);
        String expectedString = "[T][ ] Read book"; // Assuming super.toString() is implemented as expected
        assertEquals(expectedString, todo.toString());
    }
}
