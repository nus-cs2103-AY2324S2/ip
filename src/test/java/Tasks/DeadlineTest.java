package tasks;

import org.junit.jupiter.api.Test;

import exceptions.KewgyException;
import tasks.Deadline;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testDeadlineCreationWithFormattedDate() throws KewgyException {
        Deadline deadline = new Deadline("Buy groceries /by 2024-02-15");
        assertEquals("[D][ ] Buy groceries (by: Feb 15 2024)", deadline.toString());
        assertFalse(deadline.isDone);
    }

    @Test
    void testDeadlineCreationWithUnformattedDate() throws KewgyException {
        Deadline deadline = new Deadline("Buy groceries /by abc");
        assertEquals("[D][ ] Buy groceries (by: abc)", deadline.toString());
        assertFalse(deadline.isDone);
    }

    @Test
    void testEmptyDescription() {
        assertThrows(KewgyException.class, () -> new Deadline(""));
    }

    @Test
    void testMissingDateInDescription() {
        assertThrows(KewgyException.class, () -> new Deadline("Incomplete task description"));
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(KewgyException.class, () -> new Deadline("Invalid date format /by"));
    }

    @Test
    void testMarkAsDone() throws KewgyException {
        Deadline deadline = new Deadline("Complete assignment /by 2024-03-01");
        deadline.setDone(true);
        assertEquals("[D][X] Complete assignment (by: Mar 1 2024)", deadline.toString());
        assertTrue(deadline.isDone);
    }
}