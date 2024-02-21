package tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import anxi.tasks.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEventTest() {
        assertEquals("[E][ ] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting",
                        LocalDateTime.parse("06-08-2024 1400", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
                        LocalTime.parse("16:00", DateTimeFormatter.ofPattern("HH:mm"))).toString());
    }

    @Test
    public void createEventWithBoolTest() {
        assertEquals("[E][X] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting", true, "06-08-2024 1400", "16:00").toString());

        assertEquals("[E][ ] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting", false, "06-08-2024 1400", "16:00").toString());
    }

    @Test
    public void differentTimeFormatTest() {
        assertEquals("[E][ ] project meeting (from: Aug 06 2024 02:00 pm to: 04:00 pm)",
                new Event("project meeting",
                        LocalDateTime.parse("06-08-2024 1400", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
                        LocalTime.parse("04:00 pm", DateTimeFormatter.ofPattern("hh:mm a"))).toString());
    }

    @Test
    public void testSaveFileSyntax() {
        assertEquals("E | 0 | project meeting | 2024-08-06 14:00 | 16:00",
                new Event("project meeting",
                        LocalDateTime.parse("06-08-2024 1400", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
                        LocalTime.parse("16:00", DateTimeFormatter.ofPattern("HH:mm"))).saveFileString());
    }
}
