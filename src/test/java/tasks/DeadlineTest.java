package tasks;

import exceptions.InvalidFormatException;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void testDeadline_DateTimeParseExceptionThrown() {
        try {
            new Deadline("go home", "2023/09/02 1000");
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException e) {
        }
    }
    @Test
    public void testDeadlineOf_success() throws InvalidInputException {
        Deadline d1 = new Deadline("Submit assignment", "2025-05-20 23:59");
        assertEquals(d1.toString(),
                Deadline.DeadlineOf("deadline Submit assignment /by 2025-05-20 23:59").toString());
        Deadline d2 = new Deadline("chop cucumber", "2026-11-18 01:43");
        assertEquals(d2.toString(),
                Deadline.DeadlineOf("deadline chop cucumber /by 2026-11-18 01:43").toString());
    }

    @Test
    public void testDeadlineOf_exceptionThrown() throws InvalidInputException {
        try {
            Deadline.DeadlineOf("deadline ");
            fail("Expected InvalidFormatException to be thrown");
        } catch (InvalidFormatException e) {
        }
        try {
            Deadline.DeadlineOf("deadline play games by tmr");
            fail("Expected InvalidFormatException to be thrown");
        } catch (InvalidFormatException e) {
        }
    }

    @Test
    public void testSaveFormat() {
        Deadline d1 = new Deadline("go to school", "2021-12-14 08:00");
        d1.markTask();
        assertEquals("D | 1 | go to school | 2021-12-14 08:00\n", d1.saveFormat());
        d1.unmarkTask();
        assertEquals("D | 0 | go to school | 2021-12-14 08:00\n", d1.saveFormat());
    }

    @Test
    public void testToString() {
        Deadline d = new Deadline("sleep", "2023-10-02 23:00");
        assertEquals("[D][ ] sleep (by: 2 Oct 2023 11:00pm)", d.toString());

        Deadline d1 = new Deadline("wake up", "2024-09-08 14:38");
        assertEquals("[D][ ] wake up (by: 8 Sept 2024 02:38pm)", d1.toString());
    }

}
