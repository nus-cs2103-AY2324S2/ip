package martin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testConstructor() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("Buy groceries", todo.getDescription());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Do laundry");
        String expected = "[T][ ] Do laundry";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToFileString() {
        Todo todo = new Todo("Clean the house");
        String expected = "T | 0 | Clean the house";
        assertEquals(expected, todo.toFileString());
    }
}