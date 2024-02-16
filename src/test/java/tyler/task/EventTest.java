package tyler.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    @Test
    public void testEvent() {
        LocalDateTime start = LocalDateTime.parse("2024-02-12 0000", INPUT_DATE_FORMAT);
        LocalDateTime end = LocalDateTime.parse("2024-02-13 0000", INPUT_DATE_FORMAT);
        Event wedding = new Event("wedding", start, end);
        String test = "[E][ ] wedding (from: 12 Feb 2024 12:00 AM to: 13 Feb 2024 12:00 AM)";
        assertEquals(test, wedding.toString());

        wedding.mark();
        String test1 = "[E][X] wedding (from: 12 Feb 2024 12:00 AM to: 13 Feb 2024 12:00 AM)";
        assertEquals(test1, wedding.toString());

        String test2 = "E | 1 | wedding | 12 Feb 2024 12:00 AM | 13 Feb 2024 12:00 AM";
        assertEquals(test2, wedding.saveToFileString());
    }
}
