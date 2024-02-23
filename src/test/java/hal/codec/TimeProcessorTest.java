package hal.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class TimeProcessorTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 31, 12, 0);
        String formattedString = TimeProcessor.toString(dateTime);
        assertEquals("31-01-2024 12:00", formattedString);
    }

    @Test
    public void testFromString() {
        String formattedString = "31-01-2024 12:00";
        LocalDateTime dateTime = TimeProcessor.fromString(formattedString);
        assertEquals(2024, dateTime.getYear());
        assertEquals(1, dateTime.getMonthValue());
        assertEquals(31, dateTime.getDayOfMonth());
        assertEquals(12, dateTime.getHour());
        assertEquals(0, dateTime.getMinute());
    }

    @Test
    public void testFromStringWithDateOnly() {
        String formattedString = "31-01-2024";
        LocalDateTime dateTime = TimeProcessor.fromString(formattedString);
        assertEquals(2024, dateTime.getYear());
        assertEquals(1, dateTime.getMonthValue());
        assertEquals(31, dateTime.getDayOfMonth());
        assertEquals(0, dateTime.getHour());
        assertEquals(0, dateTime.getMinute());
    }

    @Test
    public void testFromStringWithInvalidFormat() {
        String invalidFormattedString = "2024-01-31 12:00"; // Invalid format
        assertThrows(DateTimeParseException.class, () -> TimeProcessor.fromString(invalidFormattedString));
    }
}
