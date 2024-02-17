import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Event;
import duke.Tag;

class EventTest {

    @Test
    void testToString() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 13, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 13, 12, 0);
        Event event = new Event("Team meeting", start, end);
        assertEquals("[E][ ] Team meeting (from: 2024-02-13T10:00 to: 2024-02-13T12:00)", event.toString(),
                "toString does not match expected output without tag.");

        event.addTag("Work");
        assertEquals("[E][ ] Team meeting (from: 2024-02-13T10:00 to: 2024-02-13T12:00) Work",
                event.toString(), "toString does not match expected output with tag.");
    }

    @Test
    void testToFileString() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 20, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 20, 16, 0);
        Event event = new Event("Client presentation", start, end);
        assertEquals("E | 0 | Client presentation | 2024-02-20T14:00 - 2024-02-20T16:00", event.toFileString(),
                "toFileString does not match expected output without tag.");

        event.markAsDone();
        assertEquals("E | 1 | Client presentation | 2024-02-20T14:00 - 2024-02-20T16:00", event.toFileString(),
                "toFileString does not match expected output for done task without tag.");

        event.addTag("Important");
        assertEquals("E | 1 | Client presentation | 2024-02-20T14:00 - 2024-02-20T16:00 | Important",
                event.toFileString(), "toFileString does not match expected output for done task with tag.");
    }

    @Test
    void testFromFileString() {
        String fileString = "E | 0 | Conference | 2024-03-05T09:00 - 2024-03-05T11:00";
        Event event = Event.fromFileString(fileString);
        assertNotNull(event, "Event should not be null.");
        assertEquals("Conference", event.getDescription(), "Description does not match.");
        assertEquals("2024-03-05T09:00", event.getFromDate(), "Start time does not match.");
        assertEquals("2024-03-05T11:00", event.getToDate(), "End time does not match.");
        assertFalse(event.isDone(), "Task should not be marked as done.");

        String fileStringWithTag = "E | 1 | Seminar | 2024-04-10T14:00 - 2024-04-10T17:00 | Education";
        Event eventWithTag = Event.fromFileString(fileStringWithTag);
        assertNotNull(eventWithTag, "Event with tag should not be null.");
        assertTrue(eventWithTag.isDone(), "Task should be marked as done.");
        Tag tag = eventWithTag.getTag();
        assertEquals("Education", tag.getTagName(), "Tag does not match.");
    }

    @Test
    void testAddAndRemoveTag() {
        LocalDateTime start = LocalDateTime.of(2024, 2, 25, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 2, 25, 12, 0);
        Event event = new Event("Webinar", start, end);
        Tag tag = event.getTag();
        assertNull(tag, "Initially, tag should be null.");

        event.addTag("Tech");
        Tag tag2 = event.getTag();
        assertNotNull(tag2, "Tag should not be null after adding.");
        assertEquals("Tech", tag2.getTagName(), "Tag name does not match after adding.");

        event.removeTag();
        Tag tag3 = event.getTag();
        assertNull(tag3, "Tag should be null after removal.");
    }
}
