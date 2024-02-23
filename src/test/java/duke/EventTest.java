package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testToString() {
        // Creating an Event instance with a specific start and end time
        String description = "Sample Event";
        String startTime = "2022-01-01 12:00";
        String endTime = "2022-01-01 14:00";
        try {
            Event event = new Event(description, startTime, endTime);

            // Expected string representation based on the provided start and end time
            String expectedToString = "[E][ ] Sample Event (from: Jan 01 2022 12:00 to: Jan 01 2022 14:00)";

            // Testing the toString method
            assertEquals(expectedToString, event.toString());
        } catch (DukeException e) {
            // Handle the DukeException if it occurs
            e.printStackTrace(); // Or handle it in some appropriate way
        }
    }

}
