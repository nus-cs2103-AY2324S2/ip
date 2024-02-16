package ezra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Event} class.
 */
public class EventTest {

    /**
     * Test cases for the {@link Event#toString()} method.
     */
    @Test
    public void testToString() {
        Event event1 = new Event("CS2103T briefing", "16/02/2024 1600", "16/02/2024 1700");
        String expectedString1 = "[E][ ] CS2103T briefing (from: 16 Feb 2024 04:00 PM to: 16 Feb 2024 05:00 PM)";
        assertEquals(expectedString1, event1.toString());

        Event event2 = new Event("project meeting", "18/02/2024 2100", "18/02/2024 2200");
        String expectedString2 = "[E][ ] project meeting (from: 18 Feb 2024 09:00 PM to: 18 Feb 2024 10:00 PM)";
        assertEquals(expectedString2, event2.toString());
    }

    /**
     * Test cases for the {@link Event#toStorageString()} method.
     */
    @Test
    public void testToStorageString() {
        Event event1 = new Event("CS2103T briefing", "16/02/2024 1600", "16/02/2024 1700");
        String expectedString1 = "E | 0 | CS2103T briefing | 16/02/2024 1600 | 16/02/2024 1700";
        assertEquals(expectedString1, event1.toStorageString());

        Event event2 = new Event("project meeting", "18/02/2024 2100", "18/02/2024 2200");
        String expectedString2 = "E | 0 | project meeting | 18/02/2024 2100 | 18/02/2024 2200";
        assertEquals(expectedString2, event2.toStorageString());
    }

    /**
     * Test cases for the {@link Event#equals(Object)} method.
     */
    @Test
    public void testEquals() {
        Event event = new Event("CS2103T briefing", "16/02/2024 1600", "16/02/2024 1700");
        Event sameFields = new Event("CS2103T briefing", "16/02/2024 1600", "16/02/2024 1700");
        Event differentFrom = new Event("CS2103T briefing", "16/02/2024 1500", "16/02/2024 1700");
        Event differentTo = new Event("CS2103T briefing", "16/02/2024 1600", "17/02/2024 1700");
        Event differentDescription = new Event(
                "CS2103T lecture", "16/02/2024 1600", "16/02/2024 1700");

        assertEquals(event, sameFields);
        assertNotEquals(event, differentFrom);
        assertNotEquals(event, differentTo);
        assertNotEquals(event, differentDescription);

    }
}
