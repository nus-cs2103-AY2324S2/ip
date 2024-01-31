package seedu.banter.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void eventToString() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        String expected = "[E][ ] Meeting (from: 30 Jan 2022 1000 to: 30 Jan 2022 1200)";
        String result = event.toString();
        
        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void eventGetStart() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        LocalDateTime result = event.getStart();

        assertEquals(from, result, "getStart() should return the expected start time");
    }

    @Test
    void eventGetEnd() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        LocalDateTime result = event.getEnd();

        assertEquals(to, result, "getEnd() should return the expected end time");
    }
}
