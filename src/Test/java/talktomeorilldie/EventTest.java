package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Event class.
 */
public class EventTest {

    /**
     * Tests the toString method of Event.
     */
    @Test
    public void testToString() {
        // Create an Event object
        String description = "Project Meeting";
        String fromDate = "Mon";
        LocalTime fromTime = LocalTime.of(14, 0);
        LocalTime toTime = LocalTime.of(16, 0);
        Event event = new Event(description, fromDate, fromTime, toTime);

        // Define the expected output string
        String expected = "[E][ ] Project Meeting (from: Mon 2:00PM to: 4:00PM)";

        // Check if the toString method produces the expected output
        assertEquals(expected, event.toString());
    }
}
