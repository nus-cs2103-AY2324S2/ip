package adam.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void testDeadline(){
        try {
            Deadline d = new Deadline("description", "2001-10-10");
        } catch (Exception e) {
            fail("Deadline not created");
        }
    }
    @Test
    public void testDeadlineInvalidDate(){
        try {
            Deadline d = new Deadline("description", "not a date");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format", e.getMessage());
        }
        try {
            Deadline d = new Deadline("description", "10 Jan 2001");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format", e.getMessage());
        }
        try {
            Deadline d = new Deadline("description", "2001/01/10");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Invalid date or date format", e.getMessage());
        }
    }

    @Test
    public void testToFileString(){
        try {
            Deadline d = new Deadline("description", "2001-10-10");
            assertEquals("[D][ ] description (by: 10 Oct 2001)", d.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
