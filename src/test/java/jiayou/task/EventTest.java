package jiayou.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        LocalDate from = LocalDate.parse("2010-01-01");
        LocalDate to = LocalDate.parse("2020-01-01");
        Event event = new Event("Complete homework", from, to);
        String expected = "[E][ ] Complete homework (from: Jan 1 2010 to: Jan 1 2020)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToStringForStore() {
        LocalDate from = LocalDate.parse("2010-01-01");
        LocalDate to = LocalDate.parse("2020-01-01");
        Event event = new Event("Complete homework", from, to);
        String expected = "E | 0 | Complete homework | from 2010-01-01 to 2020-01-01";
        assertEquals(expected, event.toStringForStore());
    }

    @Test
    public void testToDoDescription() {
        String description = "Complete homework";
        LocalDate from = LocalDate.parse("2010-01-01");
        LocalDate to = LocalDate.parse("2020-01-01");
        Event event = new Event("Complete homework", from, to);
        assertEquals(description, event.getDescription());
    }

    @Test
    public void testEquals() {
        LocalDate from = LocalDate.parse("2010-01-01");
        LocalDate to = LocalDate.parse("2020-01-01");
        LocalDate anotherFrom = LocalDate.parse("2010-01-02");
        LocalDate anotherTo = LocalDate.parse("2020-01-02");

        Event event = new Event("Complete homework", from, to);
        Event eventSame = new Event("Complete homework", from, to);
        Event eventDiffDesc = new Event("Complete", from, to);
        Event eventDiffFrom = new Event("Complete homework", anotherFrom, to);
        Event eventDiffTo = new Event("Complete homework", from, anotherTo);

        assertEquals(true, event.equals(eventSame));
        assertEquals(false, event.equals(eventDiffDesc));
        assertEquals(false, event.equals(eventDiffFrom));
        assertEquals(false, event.equals(eventDiffTo));
    }
}