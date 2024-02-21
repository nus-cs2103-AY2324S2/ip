package tasks;

import org.junit.jupiter.api.Test;

import exceptions.KewgyException;
import tasks.Event;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testEventCreationWithFormattedDates() throws KewgyException {
        Event event = new Event("Conference /from 2024-02-20 /to 2024-02-22");
        assertEquals("[E][ ] Conference (from: Feb 20 2024 to: Feb 22 2024)", event.toString());
        assertFalse(event.isDone);
    }

    @Test
    void testEventCreationWithUnformattedDates() throws KewgyException {
        Event event = new Event("Party /from some time /to another time");
        assertEquals("[E][ ] Party (from: some time to: another time)", event.toString());
        assertFalse(event.isDone);
    }

    @Test
    void testEmptyDescription() {
        assertThrows(KewgyException.class, () -> new Event(""));
    }

    @Test
    void testMissingDatesInDescription() {
        assertThrows(KewgyException.class, () -> new Event("Incomplete event description"));
    }

    @Test
    void testInvalidDateFormats() {
        assertThrows(KewgyException.class, () -> new Event("Invalid date format /from /to invalidDate"));
    }

    @Test
    void testMarkAsDone() throws KewgyException {
        Event event = new Event("Birthday celebration /from 2024-03-01 /to 2024-03-03");
        event.setDone(true);
        assertEquals("[E][X] Birthday celebration (from: Mar 1 2024 to: Mar 3 2024)", event.toString());
        assertTrue(event.isDone);
    }
}