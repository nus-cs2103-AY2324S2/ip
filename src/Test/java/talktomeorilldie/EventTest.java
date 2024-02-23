package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Event class.
 */
public class EventTest {

    /**
     * Tests the toString method of Event.
     */
    @Test
    public void testEventToString() {
        Event event = new Event("Description", "From", "To");
        assertEquals("[E][ ] Description (from: From to: To)", event.toString());
    }

    /**
     * Tests the toSaveString method of Event.
     */
    @Test
    public void testEventToSaveString() {
        Event event = new Event("Description", "From", "To");
        assertEquals("E | 0 | Description | From - To", event.toSaveString());
    }

    /**
     * Tests the event.
     */
    @Test
    public void testEvent() {
        Event event = new Event("Meeting", "2024-02-08 10:00", "2024-02-08 12:00");
        assertEquals("[E][ ] Meeting (from: 2024-02-08 10:00 to: 2024-02-08 12:00)", event.toString());
    }
}
