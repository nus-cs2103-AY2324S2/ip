package dune.task;

import dune.DuneException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    // correct behaviour for normal event
    @Test
    public void testEvent() {
        try {
            Event event = new Event("7-Eleven", "2001-09-11T07:59", "2001-09-11T17:20");
            assertEquals("2001-09-11T07:59", event.getStart().toString());
            assertEquals("2001-09-11T17:20", event.getEnd().toString());
            assertEquals("[E][ ] 7-Eleven (from: 11 Sep 2001 7:59 AM to: 11 Sep 2001 5:20 PM)", event.toString());
            event.mark();
            assertEquals("[E][X] 7-Eleven (from: 11 Sep 2001 7:59 AM to: 11 Sep 2001 5:20 PM)", event.toString());
            event.unmark();
            assertEquals("[E][ ] 7-Eleven (from: 11 Sep 2001 7:59 AM to: 11 Sep 2001 5:20 PM)", event.toString());
        } catch (DuneException d) {
            System.out.println(d.getMessage());
        }

    }

    // same start and end datetime, should throw exception
    @Test
    public void testEventSameStartEnd() {
        try {
            Event event = new Event("test", "2021-08-25T12:00", "2021-08-25T12:00");
        } catch (Exception e) {
            assertEquals("Start date must be before end date", e.getMessage());
        }
    }

    // unparsable datetime, should throw exception
    @Test
    public void testEventUnparsableDateTime() {
        try {
            Event event = new Event("test", "2020-x8-25T10:00", "2021-08-25T12:00");
        } catch (Exception e) {
            assertEquals("Text '2020-x8-25T10:00' could not be parsed at index 5", e.getMessage());
        }
    }

}
