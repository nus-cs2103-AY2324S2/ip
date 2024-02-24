package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getStorageFormat() {
        assertEquals("E | false | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .getStorageFormat());

        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        event.setDone(true);
        assertEquals("E | true | a | 2024-02-11T19:37:00 | 2024-02-12T19:37:00", event.getStorageFormat());
    }

    @Test
    public void isOccurringOn() {
        assertFalse(new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 10)));

        assertTrue(new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 11)));

        assertTrue(new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 12)));

        assertTrue(new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 13)));

        assertFalse(new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0))
                .isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 14)));
    }

    @Test
    public void toString_notDone() {
        assertEquals("[E][ ] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toString());
    }

    @Test
    public void toString_done() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        event.setDone(true);
        assertEquals("[E][X] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", event.toString());
    }
}
