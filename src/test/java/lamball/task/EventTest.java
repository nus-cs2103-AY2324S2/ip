package lamball.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void eventTest() {
        // Command Test
        assertEquals(new Event("TEST", "2000-01-01", "2000-01-01").command(), "event TEST " +
                "/from 2000-01-01 /to 2000-01-01");

        // String Test
        assertEquals(new Event("TEST", "2000-01-01", "2000-01-01").toString(),
                "[E][ ] TEST (from: Jan 1 2000 to: Jan 1 2000)");

        // Mark test
        Event test = new Event("TEST", "2000-01-01", "2000-01-01");
        test.mark();
        assertEquals(test.toString(), "[E][X] TEST (from: Jan 1 2000 to: Jan 1 2000)");

        // Unmark test
        test.unMark();
        assertEquals(test.toString(), "[E][ ] TEST (from: Jan 1 2000 to: Jan 1 2000)");

        // DateTimeParseException test
        DateTimeParseException thrown = Assertions.assertThrows(DateTimeParseException.class, () -> {
            Event test2 = new Event("TEST", "abcdefg", "2000-01-01");
        });

        // Error Message test
        assertEquals("Text 'abcdefg' could not be parsed at index 0", thrown.getMessage());

    }
}
