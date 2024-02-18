package fishstock.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskFactoryTest {
    @Test
    public void fromStorageString_validTask_success() throws Exception {
        assertEquals("[T][X] borrow book", TaskFactory.fromStorageString("T|borrow book|1").toString());

        assertEquals("[D][ ] return book (by: Jan 27 2024, 5:15PM)",
                TaskFactory.fromStorageString("D|return book|27/1/2024 17:15|0").toString());

        assertEquals("[E][X] project meeting (from: Jan 01 1998, 12:00PM to: Jan 01 1998, 10:00PM)",
                TaskFactory.fromStorageString("E|project meeting|1/1/1998 12:00|1/1/1998 22:00|1").toString());
    }

    @Test
    public void fromStorageString_invalidTask_exceptionThrown() {
        try {
            // Missing mark
            TaskFactory.fromStorageString("T|borrow book|");
            fail();
        } catch (Exception e) {
            assertEquals("Mark status corrupted..", e.getMessage());
        }

        try {
            // Missing task type
            TaskFactory.fromStorageString("return book|27/1/2024 17:15|0");
            fail();
        } catch (Exception e) {
            assertEquals("Wrong format..", e.getMessage());
        }

        try {
            // Invalid date format
            TaskFactory.fromStorageString("E|project meeting|1/1/98 22:00|1/1/1998 13|1");
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Dates should be of the format <dd/mm/yyyy hh:mm>", e.getMessage());
        }
    }
}
