package ghbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * EventTest Class.
 */
public class EventTest {
    @Test
    public void testUnmarkToFile() {
        Event event = new Event("house warming", "21 Jan 2023 1000AM", "21 Jan 2023 0600PM");
        assertEquals("E | 0 | house warming | 21 Jan 2023 1000AM | 21 Jan 2023 0600PM", event.toFile());
    }

    @Test
    public void testMarkToFile() {
        Event event = new Event("house warming", "21 Jan 2023 1000AM", "21 Jan 2023 0600PM");
        event.isCompleted();
        assertEquals("E | 1 | house warming | 21 Jan 2023 1000AM | 21 Jan 2023 0600PM", event.toFile());
    }

    @Test
    public void testUnmarkToString() {
        Event event = new Event("house warming", "21 Jan 2023 1000AM", "21 Jan 2023 0600PM");
        assertEquals("[E][ ] house warming (from: 21 Jan 2023 1000AM to: 21 Jan 2023 0600PM)", event.toString());
    }

    @Test
    public void testMarkToString() {
        Event event = new Event("house warming", "21 Jan 2023 1000AM", "21 Jan 2023 0600PM");
        event.isCompleted();
        assertEquals("[E][X] house warming (from: 21 Jan 2023 1000AM to: 21 Jan 2023 0600PM)", event.toString());
    }
}
