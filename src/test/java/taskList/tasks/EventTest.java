package tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event event = new Event("career event", "/from 20-11-2020 0000", "/to 1-12-2021 1100");
        assertEquals("[E][ ] career event (from: 20-11-2020 0000 to: 1-12-2021 1100)", event.toString());
    }

    public void testDateTimeError() {
        Event event = new Event("career event", "/from something 20-11-2020 0000", "/to 1-12-2021 1100");
        assertEquals("Error creating Event: Date format is incorrect, please try again", event.toString());
    }

    public void invalidDateOrderError() {
        Event event = new Event("career event", "/from something 21-11-2020 0000", "/to 20-11-2020 1100");
        assertEquals("Error creating Event: Start date must be before end date", event.toString());
    }
}
