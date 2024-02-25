package sam.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sam.SamException;

public class ToDoTest {
    @Test
    public void constructor_validInput1_success() throws SamException {
        ToDo task = new ToDo("test1");
        assertEquals("[T][ ] test1", task.toString());
    }

    @Test
    public void constructor_validInput2_success() throws SamException {
        ToDo task = new ToDo("test2");
        assertEquals("T | 0 | test2", task.toFileFormat());
    }

    @Test
    public void constructor_markAsDone_success() throws SamException {
        ToDo task = new ToDo("test3");
        task.markAsDone();
        assertEquals("[T][X] test3", task.toString());
    }
}
