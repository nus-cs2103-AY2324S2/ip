package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[T][ ] eat", new Todo("eat", false).toString());
    }

    @Test
    public void testToFileStringConversion() {
        assertEquals("T|0|eat", new Todo("eat", false).toFileString());
    }
}
