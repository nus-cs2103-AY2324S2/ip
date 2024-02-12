package bob.task;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStorageFormat_notDone_success() {
        assertEquals("E | false | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toStorageFormat());
    }

    @Test
    public void toStorageFormat_done_success() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        event.setDone(true);
        assertEquals("E | true | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", event.toStorageFormat());
    }
}
