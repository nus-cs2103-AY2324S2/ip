package saopig.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        LocalDateTime startDateTime = LocalDateTime.parse("2020-02-02 18:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse("2020-02-02 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assert new Event("project meeting", startDateTime, endDateTime).toString().contains(
                "[E][ ] project meeting");
    }
}
