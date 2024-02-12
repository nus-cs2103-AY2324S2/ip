package tasks;

import exceptions.InvalidFormatException;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void test_deadline_DateTimeParseException_thrown() {
        try {
            new Deadline("go home", "2023/09/02 1000");
            fail("Expected DateTimeParseException to be thrown");
        } catch (DateTimeParseException e) {
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
        assertEquals("[D][ ] wake up (by: 8 Sep 2024 02:38pm)", d1.toString());
    }

}
