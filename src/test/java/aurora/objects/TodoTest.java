package aurora.objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    Todo todo = new Todo("Test Todo");

    @Test
    void constructorTest() {
        assertFalse(todo.getStatus());
    }

    @Test
    void toFileStringTest() {
        String expected = "T | 0 | Test Todo";
        assertEquals(expected, todo.toFileString());
    }

    @Test
    void toStringTest() {
        String expected = "[T][ ] Test Todo";
        assertEquals(expected, todo.toString());
    }
}
