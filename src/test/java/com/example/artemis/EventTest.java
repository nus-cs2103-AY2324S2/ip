package com.example.artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("Group Meeting", "01-03-2024 1800", "01-03-2024 1900");
        String expected = "[E][ ] Group Meeting (from: Mar 01 2024 6:00PM to: Mar 01 2024 7:00PM)";
        String actual = event.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToFileString() {
        Event event = new Event("Group Meeting", "01-03-2024 1800", "01-03-2024 1900");
        String expected = "E | 0 | Group Meeting | Mar 01 2024 6:00PM - Mar 01 2024 7:00PM";
        String actual = event.toFileString();
        assertEquals(expected, actual);
    }
}
