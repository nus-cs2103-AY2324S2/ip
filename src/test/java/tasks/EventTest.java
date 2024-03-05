package tasks;
import org.junit.jupiter.api.Test;
import task.Event;
import task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void newEventTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime from = LocalDateTime.parse("2/12/2023 1800", formatter);
        LocalDateTime to = LocalDateTime.parse("3/12/2023 1800", formatter);
        Event event = new Event("school", false, from, to );
        assertEquals("[E] [ ] school (from:Dec 02 2023 18:00 to:Dec 03 2023 18:00)", event.toString());
    }

    @Test
    public void markEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime from = LocalDateTime.parse("2/12/2023 1800", formatter);
        LocalDateTime to = LocalDateTime.parse("3/12/2023 1800", formatter);
        Event event = new Event("school", false, from, to );
        event.markAsDone();
        assertEquals("[E] [X] school (from:Dec 02 2023 18:00 to:Dec 03 2023 18:00)", event.toString());
    }
}
