package com.example.artemis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void testToString() throws ArtemisException {
        Deadline deadline = new Deadline("Assignment", "01-03-2024 1800");
        String expected = "[D][ ] Assignment (by: Mar 01 2024 6:00PM)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToFileString() {
        Deadline deadline = new Deadline("Assignment", "01-03-2024 1800");
        String expected = "D | 0 | Assignment | Mar 01 2024 6:00PM";
        String actual = deadline.toFileString();
        assertEquals(expected, actual);
    }
}
