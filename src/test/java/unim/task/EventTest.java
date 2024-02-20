package unim.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testToStringWithLocalDateRange() {
        LocalDate startDate = LocalDate.parse("02/18/2024", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse("02/18/2024", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Event event = new Event("team workshop", startDate, endDate);
        assertEquals("[ ] [E] team workshop (from: Feb 18 2024 to: Feb 18 2024)", event.toString());
    }
}
