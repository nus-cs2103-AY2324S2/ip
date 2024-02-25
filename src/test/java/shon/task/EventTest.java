package shon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event event1 = new Event("Project meeting", "06/02/2024 0000", "06/02/2024 1200", false);
        assertEquals("[E][ ] Project meeting (from: 06 Feb 2024 0000 to: 06 Feb 2024 1200)", event1.toString());

        Event event2 = new Event("Project meeting", "06/02/2024 0000", "06/02/2024 1200", true);
        assertEquals("[E][X] Project meeting (from: 06 Feb 2024 0000 to: 06 Feb 2024 1200)", event2.toString());
    }

    @Test
    public void testFormatData() {
        Event event1 = new Event("Project meeting", "06/02/2024 0000", "06/02/2024 1200", false);
        assertEquals("E | 0 | Project meeting | 06/02/2024 0000 | 06/02/2024 1200", event1.formatData());

        Event event2 = new Event("Project meeting", "06/02/2024 0000", "06/02/2024 1200", true);
        assertEquals("E | 1 | Project meeting | 06/02/2024 0000 | 06/02/2024 1200", event2.formatData());
    }

    @Test
    public void constructor_invalidDateTime_exceptionThrown() {
        try {
            assertEquals(new Event("", "06/02/2024 0000", "06/02/2024 0000", false),
                    new Event("Project Meeting", "06/02/2024 0000", "b", false));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text 'b' could not be parsed at index 0", e.getMessage());
        }

        try {
            assertEquals(new Event("", "06/12/2024 0000", "06/13/2024/0000", false),
                    new Event("Project Meeting", "06/12/2024 0000", "06/13/2024/0000", false));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '06/13/2024/0000' could not be parsed at index 10", e.getMessage());
        }
    }
}
