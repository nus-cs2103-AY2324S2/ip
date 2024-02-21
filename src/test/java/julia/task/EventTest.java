package julia.task;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit test cases to verify the functionality of the Event class.
 */
public class EventTest {

    @Test
    public void eventInitializationTest() {
        Event event = new Event("Event Task", "2024-01-31 12:00", "2024-01-31 14:00");
        assertEquals("Event Task", event.description);
        assertFalse(event.isDone);
        assertEquals("2024-01-31 12:00", event.start);
        assertEquals("2024-01-31 14:00", event.end);
    }

    @Test
    public void getAtTest() {
        Event event = new Event("Event Task", "2024-01-31 12:00", "2024-01-31 14:00");
        assertEquals("2024-01-31 12:00 from 2024-01-31 14:00", event.getAt());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("Event Task", "2024-01-31 12:00", "2024-01-31 14:00");
        assertEquals("[E][ ] Event Task (from: 2024-01-31 12:00 to: 2024-01-31 14:00)", event.toString());

        // Set event as done
        event.setStatus();
        assertEquals("[E][X] Event Task (from: 2024-01-31 12:00 to: 2024-01-31 14:00)", event.toString());
    }

    @Test
    public void toFileStringTest() {
        Event event = new Event("Event Task", "2024-01-31 12:00", "2024-01-31 14:00");
        assertEquals("E | 0 | Event Task | 2024-01-31 12:00 from 2024-01-31 14:00", event.toFileString());

        // Set event as done
        event.setStatus();
        assertEquals("E | 1 | Event Task | 2024-01-31 12:00 from 2024-01-31 14:00", event.toFileString());
    }
}
