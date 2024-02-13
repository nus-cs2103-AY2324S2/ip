package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for DukeException class.
 */
public class TodoTest {

    /**
     * Tests the toString method of Todo.
     */
    @Test
    public void testToString() {
        Todo todo = new Todo("Test Todo");
        assertEquals("[T][ ] Test Todo", todo.toString());
    }
}
