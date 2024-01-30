package lamball.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void deadlineTest() {
        // Command Test
        assertEquals(new Deadline("TEST", "2000-01-01").command(), "deadline TEST /by 2000-01-01");

        // String Test
        assertEquals(new Deadline("TEST", "2000-01-01").toString(), "[D][ ] TEST (by: Jan 1 2000)");

        // Mark test
        Deadline test = new Deadline("TEST", "2000-01-01");
        test.mark();
        assertEquals(test.toString(), "[D][X] TEST (by: Jan 1 2000)");

        // Unmark test
        test.unMark();
        assertEquals(test.toString(), "[D][ ] TEST (by: Jan 1 2000)");

        // DateTimeParseException test
        DateTimeParseException thrown = Assertions.assertThrows(DateTimeParseException.class, () -> {
            Deadline test2 = new Deadline("TEST", "abcdefg");
        });

        // Error Message test
        assertEquals("Text 'abcdefg' could not be parsed at index 0", thrown.getMessage());

    }
}
