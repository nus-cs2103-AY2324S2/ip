package commands;

import org.junit.jupiter.api.Test;
import tasks.Event;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void event_InTaskList() {
        Event event = new Event("team meeting",
                LocalDateTime.of(2024, 2, 9, 20, 0),
                LocalDateTime.of(2024, 2, 9, 21, 30));
        assertEquals("[E][ ] team meeting (from: 09 Feb 2024 | 08:00 PM to: 09 Feb 2024 | 09:30 PM)",
                event.toString());
    }

    @Test
    public void event_InHardDisk() {
        Event event = new Event("team meeting",
                LocalDateTime.of(2024, 2, 9, 20, 0),
                LocalDateTime.of(2024, 2, 9, 21, 30));
        assertEquals("E| 0 | team meeting | 2024-02-09T20:00 to 2024-02-09T21:30", event.toStringForFile());
    }

    @Test
    public void markAsDone_InTaskList() {
        Event event = new Event("team meeting",
                LocalDateTime.of(2024, 2, 9, 20, 0),
                LocalDateTime.of(2024, 2, 9, 21, 30));
        event.markAsDone();
        assertEquals("[E][X] team meeting (from: 09 Feb 2024 | 08:00 PM to: 09 Feb 2024 | 09:30 PM)",
                event.toString());
    }

    @Test
    public void markAsDone_InHardDisk() {
        Event event  = new Event("team meeting",
                LocalDateTime.of(2024, 2, 9, 20, 0),
                LocalDateTime.of(2024, 2, 9, 21, 30));
        event.markAsDone();
        assertEquals("E| 1 | team meeting | 2024-02-09T20:00 to 2024-02-09T21:30", event.toStringForFile());
    }
}