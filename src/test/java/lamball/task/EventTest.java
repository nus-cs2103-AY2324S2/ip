package lamball.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventCommandTest() {
        // Command Test
        assertEquals(new Event("TEST", "2099-01-01", "2099-01-01").command(), "event TEST "
                + "/from 2099-01-01 /to 2099-01-01");
    }

    @Test
    public void eventStringTest() {
        // String Test
        assertEquals(new Event("TEST", "2099-01-01", "2099-01-01").toString(),
                "[E][ ] TEST (from: Jan 1 2099 to: Jan 1 2099)");
    }

    @Test
    public void eventMarkTest() {
        // Mark test
        Event test = new Event("TEST", "2099-01-01", "2099-01-01");
        test.mark();
        assertEquals(test.toString(), "[E][X] TEST (from: Jan 1 2099 to: Jan 1 2099)");

        // Unmark test
        test.unMark();
        assertEquals(test.toString(), "[E][ ] TEST (from: Jan 1 2099 to: Jan 1 2099)");
    }

    @Test
    public void eventExceptionTest() {
        // DateTimeParseException test
        DateTimeParseException thrown = Assertions.assertThrows(DateTimeParseException.class, () -> {
            Event test2 = new Event("TEST", "abcdefg", "2099-01-01");
        });

        // Error Message test
        assertEquals("Text 'abcdefg' could not be parsed at index 0", thrown.getMessage());

    }
}
