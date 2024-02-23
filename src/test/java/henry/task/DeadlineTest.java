package henry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import henry.HenryException;

public class DeadlineTest {
    @Test
    public void constructor_validInput1_success() {
        try {
            Deadline deadline = new Deadline("test1", "24/2/2024 1200");
            assertEquals("[D][ ] test1 (by: Feb 24 2024 12:00)", deadline.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_validInput2_success() {
        try {
            Deadline deadline = new Deadline("test1", "24/2/2024 1200");
            assertEquals("D | 0 | test1 | 24/2/2024 1200", deadline.toFileString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_markAsDone_success() {
        try {
            Deadline deadline = new Deadline("test1", "24/2/2024 1200");
            deadline.markAsDone();
            assertEquals("[D][X] test1 (by: Feb 24 2024 12:00)", deadline.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput1_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("", "24/2/2024 1200");
            fail();
        } catch (HenryException e) {
            assertEquals("No description of task!", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidInput2_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("test1", "");
            fail();
        } catch (HenryException e) {
            assertEquals("No due date specified!", e.getMessage());
        }
    }
}
