package seedu.banter.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    void deadlineInitialization() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", by);

        assertEquals("Assignment", deadline.getDescription(),
                "Description should be initialized correctly");
        assertFalse(deadline.isDone(), "Deadline should be initialized as undone");
        assertEquals(by, deadline.getDueDateTime(), "Due time should be initialized correctly");
    }

    @Test
    void deadlineInitializationWithDoneStatus() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", true, by);

        assertEquals("Assignment", deadline.getDescription(),
                "Description should be initialized correctly");
        assertTrue(deadline.isDone(), "Deadline should be initialized as done");
        assertEquals(by, deadline.getDueDateTime(), "Due time should be initialized correctly");
    }

    @Test
    void deadlineInitializationWithUndoneStatus() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", false, by);

        assertEquals("Assignment", deadline.getDescription(),
                "Description should be initialized correctly");
        assertFalse(deadline.isDone(), "Deadline should be initialized as undone");
        assertEquals(by, deadline.getDueDateTime(), "Due time should be initialized correctly");
    }

    @Test
    void deadlineToString() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", false, by);

        String expected = "[D][ ] Assignment (by: 30 Jan 2100 1000)";
        String result = deadline.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void deadlineToStringWithDoneStatus() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", true, by);

        String expected = "[D][X] Assignment (by: 30 Jan 2100 1000)";
        String result = deadline.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void deadlineToStringWithUndoneStatus() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", false, by);

        String expected = "[D][ ] Assignment (by: 30 Jan 2100 1000)";
        String result = deadline.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void deadlineGetTaskType() {
        Deadline deadline = new Deadline("Assignment",
                LocalDateTime.of(2100, 1, 30, 10, 0));

        String result = deadline.getTaskTypeIcon();

        assertEquals("D", result, "getTaskTypeIcon() should return the expected icon");
    }

    @Test
    void deadlineGetDueDateTime() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", by);

        LocalDateTime result = deadline.getDueDateTime();

        assertEquals(by, result, "getDueDateTime() should return the expected due date");
    }

    @Test
    void deadlineGetDateTimePriority() {
        LocalDateTime by = LocalDateTime.of(2100, 1, 30, 10, 0);
        Deadline deadline = new Deadline("Assignment", by);

        LocalDateTime result = deadline.getDateTimePriority();

        assertEquals(by, result, "getDateTimePriority() should return the expected due date");
    }
}
