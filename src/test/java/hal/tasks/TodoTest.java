package hal.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private Todo todo;

    @BeforeEach
    public void setup() {
        todo = new Todo("Buy groceries", false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Buy groceries", todo.getTaskName());
        assertFalse(todo.getDone());
        assertEquals("T", todo.getIdentifier());
    }

    @Test
    public void testToString() {
        assertEquals("[T] [  ] Buy groceries", todo.toString());
    }

    @Test
    public void testEncode() {
        String[] encoded = todo.encode();
        assertEquals(3, encoded.length);
        assertEquals("T", encoded[0]);
        assertEquals("false", encoded[1]);
        assertEquals("Buy groceries", encoded[2]);
    }
}
