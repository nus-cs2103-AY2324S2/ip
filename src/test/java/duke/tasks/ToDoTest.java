package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
/**
 * Encapsulate the test for ToDo class.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class ToDoTest {
    @Test
    public void ToDo_toString() {
        assertEquals("[T][X] homework", new ToDo("homework", true).toString());
    }

    @Test
    public void ToDo_toStorageFormat() {
        assertEquals("T | X | homework", new ToDo("homework", true).convertToStorageFormat());
    }
}
