package johnny.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toString_unMarkedEvent_success() {
        Event event = new Event("borrow book",
                LocalDateTime.parse("2001-07-14T00:30"),
                LocalDateTime.parse("2001-08-14T12:30"));
        assertEquals("[E][ ] borrow book (from: Jul 14 2001 12:30 AM to: Aug 14 2001 12:30 PM)",
                event.toString());
    }

    @Test
    public void toString_markedEvent_success() {
        Event event = new Event("borrow book",
                LocalDateTime.parse("2001-07-14T00:30"),
                LocalDateTime.parse("2001-08-14T12:30"));
        event.mark();
        assertEquals("[E][X] borrow book (from: Jul 14 2001 12:30 AM to: Aug 14 2001 12:30 PM)",
                event.toString());
    }

    @Test
    public void addToFile_unMarkedEvent_success() {
        Event event = new Event("borrow book",
                LocalDateTime.parse("2001-07-14T00:30"),
                LocalDateTime.parse("2001-08-14T12:30"));
        assertEquals("E | 0 | borrow book | 2001/07/14 0030 | 2001/08/14 1230\n", event.addToFile());
    }

    @Test
    public void addToFile_markedEvent_success() {
        Event event = new Event("borrow book",
                LocalDateTime.parse("2001-07-14T00:30"),
                LocalDateTime.parse("2001-08-14T12:30"));
        event.mark();
        assertEquals("E | 1 | borrow book | 2001/07/14 0030 | 2001/08/14 1230\n", event.addToFile());
    }

}
