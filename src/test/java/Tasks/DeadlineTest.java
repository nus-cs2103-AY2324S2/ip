package Tasks;

import Exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testDeadlineCreationWithFormattedDate() throws DukeException {
        Deadline deadline = new Deadline("Buy groceries /by 2024-02-15");
        assertEquals("[D][ ] Buy groceries (by: Feb 15 2024)", deadline.toString());
        assertFalse(deadline.isDone);
    }

    @Test
    void testDeadlineCreationWithUnformattedDate() throws DukeException {
        Deadline deadline = new Deadline("Buy groceries /by abc");
        assertEquals("[D][ ] Buy groceries (by: abc)", deadline.toString());
        assertFalse(deadline.isDone);
    }

    @Test
    void testEmptyDescription() {
        assertThrows(DukeException.class, () -> new Deadline(""));
    }

    @Test
    void testMissingDateInDescription() {
        assertThrows(DukeException.class, () -> new Deadline("Incomplete task description"));
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(DukeException.class, () -> new Deadline("Invalid date format /by"));
    }

    @Test
    void testMarkAsDone() throws DukeException {
        Deadline deadline = new Deadline("Complete assignment /by 2024-03-01");
        deadline.setDone(true);
        assertEquals("[D][X] Complete assignment (by: Mar 1 2024)", deadline.toString());
        assertTrue(deadline.isDone);
    }
}