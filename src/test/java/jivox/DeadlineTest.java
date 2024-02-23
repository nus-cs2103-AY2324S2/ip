package jivox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jivox.task.Deadline;

public class DeadlineTest {

    private Deadline deadline;
    private LocalDateTime deadlineDateTime;

    @BeforeEach
    public void setUp() {
        deadlineDateTime = LocalDateTime.of(2023, 2, 15, 13, 30);
        deadline = new Deadline("Submit report", deadlineDateTime);
    }

    @Test
    public void testGetType() {
        assertEquals("D", deadline.getType());
    }

    @Test
    public void testGetDeadline() {
        assertEquals(deadlineDateTime, deadline.getDeadline());
    }

    @Test
    public void testSaveFormat() {
        String expected = "D | 0 | Submit report | 2023-02-15 13:30 tag None";
        assertEquals(expected, deadline.saveFormat());
    }

    @Test
    public void testToString() {
        String expected = "[D][ ] Submit report (by: 15 Feb 2023 13:30)";
        assertEquals(expected, deadline.toString());
    }
}
