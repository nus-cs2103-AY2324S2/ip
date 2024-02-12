package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void isOccurringOn_beforeFrom_false() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0));
        assertFalse(event.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 10)));
    }

    @Test
    public void isOccurringOn_from_true() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0));
        assertTrue(event.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 11)));
    }

    @Test
    public void isOccurringOn_betweenFromAndTo_true() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0));
        assertTrue(event.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 12)));
    }

    @Test
    public void isOccurringOn_to_true() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0));
        assertTrue(event.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 13)));
    }

    @Test
    public void isOccurringOn_afterTo_false() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 13, 19, 37, 0));
        assertFalse(event.isOccurringOn(LocalDate.of(2024, Month.FEBRUARY, 14)));
    }

    @Test
    public void toString_notDone_success() {
        assertEquals("[E][ ] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0))
                .toString());
    }

    @Test
    public void toString_done_success() {
        Event event = new Event("a",
                LocalDateTime.of(2024, Month.FEBRUARY, 11, 19, 37, 0),
                LocalDateTime.of(2024, Month.FEBRUARY, 12, 19, 37, 0));
        event.setDone(true);
        assertEquals("[E][X] a (from: Feb 11 2024 1937 to: Feb 12 2024 1937)", event.toString());
    }
}
