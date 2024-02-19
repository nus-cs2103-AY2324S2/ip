package lamball.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineCommandTest() {
        // Command Test
        assertEquals(new Deadline("TEST", "2099-01-01").command(), "deadline TEST /by 2099-01-01");
    }

    @Test
    public void deadlineStringTest() {
        // String Test
        assertEquals(new Deadline("TEST", "2099-01-01").toString(), "[D][ ] TEST (by: Jan 1 2099)");
    }

    @Test
    public void deadlineMarkTest() {
        // Mark test
        Deadline test = new Deadline("TEST", "2099-01-01");
        test.mark();
        assertEquals(test.toString(), "[D][X] TEST (by: Jan 1 2099)");

        // Unmark test
        test.unMark();
        assertEquals(test.toString(), "[D][ ] TEST (by: Jan 1 2099)");

    }

    @Test
    public void deadlineExceptionTest() {
        // DateTimeParseException test
        DateTimeParseException thrown = Assertions.assertThrows(DateTimeParseException.class, () -> {
            Deadline test2 = new Deadline("TEST", "abcdefg");
        });

        // Error Message test
        assertEquals("Text 'abcdefg' could not be parsed at index 0", thrown.getMessage());
    }
}
