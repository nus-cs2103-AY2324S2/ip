package thecount.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testValidConvertStringToDate() {
        // Valid date string
        String validDateString = "2023-12-31";
        Deadline deadline = new Deadline("CS2102 project", validDateString);
        assertEquals("2023-12-31", deadline.getDeadlineTime().toString());
    }

    @Test
    public void testInvalidConvertStringToDate() {
        // Invalid date string
        String invalidDateString = "2023/12/31";
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("CS2102 project", invalidDateString);
        });
    }

    @Test
    public void testGetType() {
        Deadline deadline = new Deadline("CS2103T iP", "2023-12-31");

        assertEquals("D", deadline.getType());
    }


    @Test
    public void testToString() {
        // Valid date string
        String validDateString = "2023-12-31";
        Deadline deadline = new Deadline("CS2102 project", validDateString);
        assertEquals("[D][ ] CS2102 project (by: Dec 31 2023)", deadline.toString());
    }

}
