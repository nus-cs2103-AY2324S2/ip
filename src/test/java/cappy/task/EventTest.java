package cappy.task;

import static cappy.parser.Parser.DATE_TIME_FORMAT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void testStringConversion() {
        LocalDateTime from = LocalDateTime.parse("2023-01-29T12:00", DATE_TIME_FORMAT);
        LocalDateTime to = LocalDateTime.parse("2023-01-30T14:00", DATE_TIME_FORMAT);
        Event event = new Event("test", from, to);
        assertEquals("[E][ ] test (from: 2023-01-29T12:00 to: 2023-01-30T14:00)", event.toString());
    }
}
