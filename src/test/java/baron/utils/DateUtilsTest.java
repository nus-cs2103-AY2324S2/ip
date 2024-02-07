package baron.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateUtilsTest {
    @Test
    public void parseDateString_correctDateFormat_dateReturned() {
        String dateString = "9/10/1999 1600";
        LocalDateTime actual = DateUtils.parseDate(dateString);
        LocalDateTime expected = LocalDateTime.of(1999, 10, 9, 16, 0, 0);
        assertEquals(actual, expected);
    }

    @Test
    public void parseDateString_incorrectDateFormat_dateReturned() {
        // Invalid day
        assertThrows(IllegalArgumentException.class, () -> {
            String dateString = "32/10/1999 1600";
            LocalDateTime actual = DateUtils.parseDate(dateString);
        });

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> {
            String dateString = "1/13/1999 1600";
            LocalDateTime actual = DateUtils.parseDate(dateString);
        });

        // Invalid year
        assertThrows(IllegalArgumentException.class, () -> {
            String dateString = "1/13/199 1600";
            LocalDateTime actual = DateUtils.parseDate(dateString);
        });
    }

    @Test
    public void parseDateString_missingValues_dateReturned() {
        // No time
        assertThrows(IllegalArgumentException.class, () -> {
            String dateString = "32/10/1999";
            LocalDateTime actual = DateUtils.parseDate(dateString);
        });

        // No month
        assertThrows(IllegalArgumentException.class, () -> {
            String dateString = "1/1999 1600";
            LocalDateTime actual = DateUtils.parseDate(dateString);
        });
    }

    @Test()
    public void formatDate_dateStringReturned() {
        LocalDateTime input = LocalDateTime.of(1999, 10, 9, 16, 0, 0);
        String actual = DateUtils.formatDate(input);
        String expected = "9/10/1999, 4PM";
        assertEquals(actual, expected);
    }
}
