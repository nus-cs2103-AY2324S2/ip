package dude.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EventTest {

    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.parse("2020-12-12T18:00");
        LocalDateTime to = LocalDateTime.parse("2020-12-18T20:00");
        Event event = new Event("test", from, to);
        assertEquals("[E][ ] test (from: Dec 12, 2020 @ 6:00PM to: Dec 18, 2020 @ 8:00PM)", event.toString());
    }

    @Test
    public void testEqualsCorrectScenario() {
        LocalDateTime from = LocalDateTime.parse("2020-12-12T18:00");
        LocalDateTime to = LocalDateTime.parse("2020-12-18T20:00");
        Event event = new Event("test", from, to);
        Event event2 = new Event("test", from, to);
        assertEquals(event, event2);
    }

    @Test
    public void testEqualsWrongScenario() {
        LocalDateTime from = LocalDateTime.parse("2020-12-12T18:00");
        LocalDateTime to = LocalDateTime.parse("2020-12-18T20:00");
        Event event = new Event("test", from, to);
        Event event2 = new Event("test other description", from, to);
        assertNotEquals(event, event2);

        LocalDateTime from2 = LocalDateTime.parse("2020-12-10T18:00");
        Event event3 = new Event("test", from2, to);
        assertNotEquals(event, event3);

        LocalDateTime to2 = LocalDateTime.parse("2020-12-21T20:01");
        Event event4 = new Event("test", from, to2);
        assertNotEquals(event, event4);
    }
}
