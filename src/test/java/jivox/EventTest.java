package jivox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jivox.task.Event;

public class EventTest {

    private Event event;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    public void setUp() {
        start = LocalDateTime.of(2023, 3, 10, 9, 0);
        end = LocalDateTime.of(2023, 3, 10, 12, 0);
        event = new Event("Team meeting", start, end);
    }

    @Test
    public void testGetType() {
        assertEquals("E", event.getType());
    }

    @Test
    public void testGetStart() {
        assertEquals(start.toString(), event.getStart());
    }

    @Test
    public void testGetDeadline() {
        assertEquals(end, event.getDeadline());
    }

    @Test
    public void testSaveFormat() {
        String expected = "E | 0 | Team meeting | 2023-03-10 09:00 to 2023-03-10 12:00 tag None";
        assertEquals(expected, event.saveFormat());
    }

    @Test
    public void testToString() {
        String expected = "[E][ ] Team meeting (from: 10 Mar 2023 09:00 to: 10 Mar 2023 12:00)";
        assertEquals(expected, event.toString());
    }
}
