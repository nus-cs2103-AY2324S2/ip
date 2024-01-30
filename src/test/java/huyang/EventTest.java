package huyang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class EventTest {

    @Test
    public void testFromFileFormat_ValidInput_ReturnsEvent() throws TaskException {
        String input = "E | 0 | Project meeting | 2024-01-31T14:00:00 to 2024-01-31T16:00:00";
        Event event = Event.fromFileFormat(input);

        assertEquals("Project meeting", event.getTaskName());
        assertEquals(LocalDateTime.of(2024, 1, 31, 14, 0), event.getStart());
        assertEquals(LocalDateTime.of(2024, 1, 31, 16, 0), event.getEnd());
        assertFalse(event.isDone());
    }

    @Test
    public void testFromFileFormat_InvalidDateTimeFormat_ThrowsTaskException() {
        String input = "E | 0 | Project meeting | NotADateTime to NotADateTime";
        assertThrows(TaskException.class, () -> Event.fromFileFormat(input));
    }


    @Test
    public void testToString_ReturnsFormattedString() {
        LocalDateTime start = LocalDateTime.of(2024, 1, 31, 14, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 31, 16, 0);
        Event event = new Event("Project meeting", start, end);

        String expectedString = "[E][ ] Project meeting (from: Jan 31 2024 14:00 to: Jan 31 2024 16:00)";
        assertEquals(expectedString, event.toString());
    }
}
