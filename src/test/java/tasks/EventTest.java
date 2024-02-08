package tasks;

import duke.tasks.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEventTest() {
        assertEquals("[E][ ] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting", "06-08-2024 1400", "16:00").toString());
    }

    @Test
    public void differentTimeFormatTest() {
        assertEquals("[E][ ] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting", "06-08-2024 1400", "04:00 pm").toString());
    }

    @Test
    public void testSaveFileSyntax() {
        assertEquals("E | 0 | project meeting | 2024-08-06 14:00 | 16:00",
                new Event("project meeting", "06-08-2024 1400", "1600").saveFileString());
    }
}
