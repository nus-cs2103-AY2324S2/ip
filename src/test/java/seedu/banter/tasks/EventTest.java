package seedu.banter.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class EventTest {
    @Test
    void eventInitialization() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", from, to);

        assertEquals("Meeting", event.getDescription(),
                "Description should be initialized correctly");
        assertFalse(event.isDone(), "Event should be initialized as undone");
        assertEquals(from, event.getStartDateTime(), "Start time should be initialized correctly");
        assertEquals(to, event.getEndDateTime(), "End time should be initialized correctly");
    }

    @Test
    void eventInitializationWithDoneStatus() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", true, from, to);

        assertEquals("Meeting", event.getDescription(),
                "Description should be initialized correctly");
        assertTrue(event.isDone(), "Event should be initialized as done");
        assertEquals(from, event.getStartDateTime(), "Start time should be initialized correctly");
        assertEquals(to, event.getEndDateTime(), "End time should be initialized correctly");
    }

    @Test
    void eventInitializationWithUndoneStatus() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        assertEquals("Meeting", event.getDescription(),
                "Description should be initialized correctly");
        assertFalse(event.isDone(), "Event should be initialized as undone");
        assertEquals(from, event.getStartDateTime(), "Start time should be initialized correctly");
        assertEquals(to, event.getEndDateTime(), "End time should be initialized correctly");
    }

    @Test
    void eventToString() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        String expected = "[E][ ] Meeting (from: 30 Jan 2100 1000 to: 30 Jan 2100 1200)";
        String result = event.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void eventToStringWithDoneStatus() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", true, from, to);

        String expected = "[E][X] Meeting (from: 30 Jan 2100 1000 to: 30 Jan 2100 1200)";
        String result = event.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void eventToStringWithUndoneStatus() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        String expected = "[E][ ] Meeting (from: 30 Jan 2100 1000 to: 30 Jan 2100 1200)";
        String result = event.toString();

        assertEquals(expected, result, "toString() should return the expected string representation");
    }

    @Test
    void eventGetTaskType() {
        Event event = new Event("Meeting", false, LocalDateTime.now(), LocalDateTime.now());

        String result = event.getTaskType();

        assertEquals("E", result, "getTaskType() should return the expected task type");
    }

    @Test
    void eventGetStartTime() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        LocalDateTime result = event.getStartDateTime();

        assertEquals(from, result, "getStartDateTime() should return the expected start time");
    }

    @Test
    void eventGetEndTime() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        LocalDateTime result = event.getEndDateTime();

        assertEquals(to, result, "getEndDateTime() should return the expected end time");
    }

    @Test
    void eventGetDateTimePriority() {
        LocalDateTime from = LocalDateTime.of(2100, 1, 30, 10, 0);
        LocalDateTime to = LocalDateTime.of(2100, 1, 30, 12, 0);
        Event event = new Event("Meeting", false, from, to);

        LocalDateTime result = event.getDateTimePriority();

        assertEquals(from, result, "getDateTimePriority() should return the expected start time");
    }
}
