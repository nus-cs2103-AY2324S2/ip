package hal.tasks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class EventTest {
    private Event event;

    @BeforeEach
    public void setup() {
        LocalDateTime fromDateTime = LocalDateTime.of(2024, 1, 31, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2024, 1, 31, 12, 0);
        event = new Event("Meeting", fromDateTime, toDateTime, false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Meeting", event.getTaskName());
        assertFalse(event.getDone());
        assertEquals("E", event.getIdentifier());
        LocalDateTime expectedFromDateTime = LocalDateTime.of(2024, 1, 31, 10, 0);
        LocalDateTime expectedToDateTime = LocalDateTime.of(2024, 1, 31, 12, 0);
        assertEquals(expectedFromDateTime, event.getFrom());
        assertEquals(expectedToDateTime, event.getTo());
    }

    @Test
    public void testToString() {
        assertEquals("[E] [  ] Meeting from: 31-01-2024 10:00 to: 31-01-2024 12:00", event.toString());
    }

    @Test
    public void testEncode() {
        String[] encoded = event.encode();
        assertEquals(5, encoded.length);
        assertEquals("E", encoded[0]);
        assertEquals("false", encoded[1]);
        assertEquals("Meeting", encoded[2]);
        assertEquals("31-01-2024 10:00", encoded[3]);
        assertEquals("31-01-2024 12:00", encoded[4]);
    }
}
