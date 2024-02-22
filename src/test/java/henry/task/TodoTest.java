package henry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import henry.HenryException;

public class TodoTest {
    @Test
    public void constructor_validInput1_success() {
        try {
            Todo todo = new Todo("test1");
            assertEquals("[T][ ] test1", todo.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_validInput2_success() {
        try {
            Todo todo = new Todo("test2");
            assertEquals("T | 0 | test2", todo.toFileString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_markAsDone_exceptionThrown() {
        try {
            Todo todo = new Todo("test3");
            todo.markAsDone();
            assertEquals("[T][X] test3", todo.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput1_exceptionThrown() {
        try {
            Todo todo = new Todo("");
            fail();
        } catch (HenryException e) {
            assertEquals("No description of task!", e.getMessage());
        }
    }
}
