package martin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testConstructor() {
        Event event = new Event("Meeting with team", "10:00 AM", "11:30 AM");
        assertEquals("Meeting with team", event.getDescription());
    }

    @Test
    public void testToString() {
        Event event = new Event("Meeting with team", "10:00 AM", "11:30 AM");
        String expected = "[E][ ] Meeting with team (from: 10:00 AM to: 11:30 AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFileString() {
        Event event = new Event("Meeting with team", "10:00 AM", "11:30 AM");
        String expected = "E | 0 | Meeting with team | 10:00 AM-11:30 AM";
        assertEquals(expected, event.toFileString());
    }
}