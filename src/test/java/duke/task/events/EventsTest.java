package duke.task.events;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventsTest {
    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime to = LocalDateTime.parse("2012-12-12 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Events events = new Events("Project meeting", from, to);
        events.markAsDone();
        assertEquals("  [E][X] Project meeting (from: Dec 12 2012 12:00 to: Dec 12 2012 18:00)", events.toString());
    }

    @Test
    public void testToFile() {
        LocalDateTime from = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime to = LocalDateTime.parse("2012-12-12 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Events events = new Events("Project meeting", from, to);
        events.markAsDone();
        assertEquals("E|1|Project meeting|2012-12-12 12:00|2012-12-12 18:00", events.toFile());
    }
}
