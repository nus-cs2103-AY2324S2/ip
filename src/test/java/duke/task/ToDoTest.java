package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the method in ToDo class.
 */
public class ToDoTest {

    /**
     * Tests the toString method in ToDo class.
     */
    @Test
    public void toStringTest() {
        ToDo toDo = new ToDo("Read a book");
        String expected = "[T][ ] Read a book";
        String result = toDo.toString();
        assertEquals(expected, result);
    }
}
