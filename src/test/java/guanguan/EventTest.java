package guanguan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void saveToTextTest() {
        Event event = new Event("project meeting", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-05"));
        event.markDone();

        assertEquals("E | 1 | project meeting | 2024-01-01 | 2024-01-05", event.saveToText());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("project meeting", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-05"));

        assertEquals("[E][ ] project meeting (from: Jan 01 2024 to: Jan 05 2024)", event.toString());
    }
}
