package ezra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link ToDo} class.
 */
public class ToDoTest {

    /**
     * Test cases for the {@link ToDo#toString()} method.
     */
    @Test
    public void testToString() {
        ToDo todo1 = new ToDo("Return book");
        String expectedString1 = "[T][ ] Return book";
        assertEquals(expectedString1, todo1.toString());

        ToDo todo2 = new ToDo("Finish assignment");
        String expectedString2 = "[T][ ] Finish assignment";
        assertEquals(expectedString2, todo2.toString());
    }

    /**
     * Test cases for the {@link ToDo#toStorageString()} method.
     */
    @Test
    public void testToStorageString() {
        ToDo todo1 = new ToDo("Return book");
        String expectedString1 = "T | 0 | Return book";
        assertEquals(expectedString1, todo1.toStorageString());

        ToDo todo2 = new ToDo("Finish assignment");
        String expectedString2 = "T | 0 | Finish assignment";
        assertEquals(expectedString2, todo2.toStorageString());
    }

    /**
     * Test cases for the {@link ToDo#equals(Object)} method.
     */
    @Test
    public void testEquals() {
        ToDo todo1 = new ToDo("Return book");
        ToDo todo2 = new ToDo("Return book");
        assertEquals(todo1, todo2);
    }
}
