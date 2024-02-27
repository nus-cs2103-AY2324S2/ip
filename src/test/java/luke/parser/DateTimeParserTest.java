package luke.parser;

import luke.exception.DateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {
    @Test
    public void testGetDateTime() throws DateException {
        // Test date in the format of yyyy-MM-dd HH:mm
        DateTimeParser date = new DateTimeParser("2024-02-25 13:00");
        LocalDateTime expected = LocalDateTime.of(2024, 2, 25, 13, 0);
        assertEquals(expected, date.getDateTime());

        // Test date in the format of dd-MM-yyyy HH:mm
        date = new DateTimeParser("25-02-2024 13:00");
        assertEquals(expected, date.getDateTime());

        // Test date in the format of yyyy/MM/dd HH:mm
        date = new DateTimeParser("2024/02/25 13:00");
        assertEquals(expected, date.getDateTime());

        // Test date in the format of dd/MM/yyyy HH:mm
        date = new DateTimeParser("25/02/2024 13:00");
        assertEquals(expected, date.getDateTime());

        // Test date in the format of dd/MM/yyyy HH
        date = new DateTimeParser("25/02/2024 13");
        assertEquals(expected, date.getDateTime());

        // Test date in the format of yyyy-MM-dd (without time)
        date = new DateTimeParser("2024-02-25");
        expected = LocalDateTime.of(2024, 2, 25, 0, 0);
        assertEquals(expected, date.getDateTime());

    }

    @Test
    public void testGetDateTime_DateExceptionThrown() {
        try {
            DateTimeParser date = new DateTimeParser("2024-02-25 13:00:00");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("25-2-2024 13:00");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("Feb 25 2024 13:00");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("25/02/2024 1 PM");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("25-02/2024 13:00");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("2024/02/25 ");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }

        try {
            DateTimeParser date = new DateTimeParser("02/05");
        } catch (DateException e) {
            assertEquals("Sorry! The date is not in the correct format! :'(", e.getMessage());
        }
    }

}
