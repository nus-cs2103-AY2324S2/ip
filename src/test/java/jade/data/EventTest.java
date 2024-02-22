package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jade.exception.JadeException;

/**
 * The <code>EventTest</code> class contains unit tests for the <code>Event</code> class,
 * which represents an event task within the task management system.
 * These tests verify various functionalities of the <code>Event</code> class, including
 * determining if the event occurs on a specific date, formatting the event's date and time,
 * and converting the event to a string representation.
 * The above comment is generated using ChatGPT 3.5 using the prompt:
 * "generate a block comment for the EventTest class:{code}".
 * Modified by author for higher quality.
 */
public class EventTest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Event event1;
    private Event event2;
    @BeforeEach
    public void setUp() throws JadeException {
        startTime = LocalDateTime.parse("2024-01-01 09:00 am",
                DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK));
        endTime = LocalDateTime.parse("2024-01-02 11:00 am",
                DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK));
        event1 = new Event("team meeting", startTime, endTime);
        event2 = new Event("team meeting", startTime, endTime, true);
    }
    @Test
    public void isSameDate() throws JadeException {
        assertTrue(event1.isSameDate(LocalDate.parse("2024-01-01")));
        assertTrue(event1.isSameDate(LocalDate.parse("2024-01-02")));
        assertFalse(event1.isSameDate(LocalDate.parse("2024-01-03")));
    }
    @Test
    public void dateTimeFormatter() throws JadeException {
        assertEquals("Jan 1 2024 09:00 am", event1.dateTimeFormatter(startTime));
        assertEquals("Jan 2 2024 11:00 am", event1.dateTimeFormatter(endTime));
    }
    @Test
    public void testStringConversion() throws JadeException {
        assertEquals("[E][ ] team meeting (from: Jan 1 2024 09:00 am to: Jan 2 2024 11:00 am)",
                event1.toString());
        assertEquals("[E][X] team meeting (from: Jan 1 2024 09:00 am to: Jan 2 2024 11:00 am)",
                event2.toString());
    }
}
