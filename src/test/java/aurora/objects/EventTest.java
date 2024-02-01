package aurora.objects;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    LocalDateTime start = LocalDateTime.of(2022, 10, 10, 9, 0);
    LocalDateTime end = LocalDateTime.of(2022, 10, 10, 11, 0);
    Event event = new Event("Test Event", start, end);

    @Test
    void constructorTest() {
        assertEquals("Test Event", event.toFileString().split(" \\| ")[2]);
        assertFalse(event.getStatus());
    }

    @Test
    void getStartEndDateTest() {
        assertEquals(start, event.getStartDate());
        assertEquals(end, event.getEndDate());
    }

    @Test
    void toFileStringTest() {
        String expected = "E | 0 | Test Event | Oct 10 2022, 09:00 | Oct 10 2022, 11:00";
        assertEquals(expected, event.toFileString());
    }

    @Test
    void toStringTest() {
        String expected = "[E][ ] Test Event (from: Oct 10 2022, 09:00 to: Oct 10 2022, 11:00)";
        assertEquals(expected, event.toString());
    }
}
