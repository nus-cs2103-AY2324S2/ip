package unim.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void testToStringWithLocalDateRange() {
        LocalDateTime startDate = LocalDateTime.parse("02/18/2024 1200",
                DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        LocalDateTime endDate = LocalDateTime.parse("02/18/2024 1330",
                DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        Event event = new Event("team workshop", startDate, endDate);
        assertEquals("[ ] [E] team workshop (from: Feb 18 2024 1200 to: Feb 18 2024 1330)", event.toString());
    }
}
