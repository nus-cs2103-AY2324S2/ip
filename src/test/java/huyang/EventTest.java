package huyang;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString_ReturnsFormattedString() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 31, 16, 0);
        Event event = new Event("Project meeting", start, end);

        String expectedString = "[E][ ] Project meeting (from: Jan 31 2024 14:00 to: Jan 31 2024 16:00)";
        assertEquals(expectedString, event.toString());
    }
}
