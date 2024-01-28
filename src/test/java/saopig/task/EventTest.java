package saopig.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        LocalDateTime startDateTime = LocalDateTime.parse("2020-02-02 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse("2020-02-02 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals("[E][ ] project meeting (from: Sunday, February 2, " +
                        "2020 at 6:00:00 PM Singapore Standard Time to Sunday, " +
                        "February 2, 2020 at 8:00:00 PM Singapore Standard Time)",
                new Event("project meeting", startDateTime, endDateTime).toString());
    }
}
