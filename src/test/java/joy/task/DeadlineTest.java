package joy.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
/**
 * This class contains JUnit test cases to verify the functionality of the Deadline class.
 */
public class DeadlineTest {
    @Test
    public void deadlineInitializationTest() {
        Deadline deadline = new Deadline("Deadline Task", "2024-01-31");
        assertEquals("Deadline Task", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("2024-01-31", deadline.originalBy);
        assertEquals("2024-01-31", deadline.by.toString());
    }

    @Test
    public void getByTest() {
        Deadline deadline = new Deadline("Deadline Task", "2024-01-31");
        assertEquals(" (by: Jan 31 2024)", deadline.getBy());
    }

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Deadline Task", "2024-01-31");
        assertEquals("[D][ ] Deadline Task (by: Jan 31 2024)", deadline.toString());

        // Set deadline as done
        deadline.setStatus();
        assertEquals("[D][X] Deadline Task (by: Jan 31 2024)", deadline.toString());
    }

    @Test
    public void toFileStringTest() {
        Deadline deadline = new Deadline("Deadline Task", "2024-01-31");
        assertEquals("D | 0 | Deadline Task | 2024-01-31", deadline.toFileString());

        // Set deadline as done
        deadline.setStatus();
        assertEquals("D | 1 | Deadline Task | 2024-01-31", deadline.toFileString());
    }
}
