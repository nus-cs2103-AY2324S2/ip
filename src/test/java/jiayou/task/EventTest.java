package jiayou.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("Complete homework", "2010-01-01", "2020-01-01");
        String expected = "[E][ ] Complete homework (from: Jan 1 2010 to: Jan 1 2020)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToStringForStore() {
        Event event = new Event("Complete homework", "2010-01-01", "2020-01-01");
        String expected = "E | 0 | Complete homework | from 2010-01-01 to 2020-01-01";
        assertEquals(expected, event.toStringForStore());
    }

    @Test
    public void testToDoDescription() {
        String description = "Complete homework";
        Event event = new Event("Complete homework", "2010-01-01", "2020-01-01");
        assertEquals(description, event.getDescription());
    }
}
