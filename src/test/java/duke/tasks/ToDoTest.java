package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
