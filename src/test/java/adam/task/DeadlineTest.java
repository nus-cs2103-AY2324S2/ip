package adam.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadlineCreatedSuccessfully() {
        try {
            Deadline d = new Deadline("description", "2001-10-10");
        } catch (Exception e) {
            fail("Deadline not created");
        }
    }
    @Test
    public void testDeadlineInvalidDate() {
        try {
            Deadline d = new Deadline("description", "not a date");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format, date format is yyyy-mm-dd", e.getMessage());
        }
        try {
            Deadline d = new Deadline("description", "10 Jan 2001");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format, date format is yyyy-mm-dd", e.getMessage());
        }
        try {
            Deadline d = new Deadline("description", "2001/01/10");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format, date format is yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void testToStringSuccessful() {
        try {
            Deadline d = new Deadline("description", "2001-10-10");
            assertEquals("[D][ ] description (by: 10 Oct 2001)", d.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
