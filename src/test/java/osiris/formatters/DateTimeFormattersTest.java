package osiris.formatters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the DateTimeFormatters class.
 */
public class DateTimeFormattersTest {

    /**
     * Test for the userInputDateFormatter method.
     */
    @Test
    public void userInputDateFormatterTest() {
        // Testing valid input
        LocalDate date = DateTimeFormatters.getInstance().formatUserInputDate("01-01-2024");
        assertNotNull(date);

        // Testing invalid input
        date = DateTimeFormatters.getInstance().formatUserInputDate("invalid");
        assertNull(date);
    }

    /**
     * Test for the userInputDateTimeFormatter method.
     */
    @Test
    public void userInputDateTimeFormatterTest() {
        // Testing valid input
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("01-01-2024 2359");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the userInputDateTimeRangeFormatter method.
     */
    @Test
    public void userInputDateTimeRangeFormatterTest() {
        // Testing valid input
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formateUserInputDateTimeRange("01-01-2024 0000", "01-01-2024 2359");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().formateUserInputDateTimeRange("invalid", "invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the storedDataDateFormatter method.
     */
    @Test
    public void storedDataDateFormatterTest() {
        // Testing valid input
        LocalDate date = DateTimeFormatters.getInstance().formatStoredDate("Jan 01 2024");
        assertNotNull(date);

        // Testing invalid input
        date = DateTimeFormatters.getInstance().formatStoredDate("invalid");
        assertNull(date);
    }

    /**
     * Test for the storedDataDateTimeFormatter method.
     */
    @Test
    public void storedDataDateTimeFormatterTest() {
        // Testing valid input
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatStoredDateTime("Jan 01 2024 8:00 AM");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().formatStoredDateTime("invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the storedDateDateTimeRangeFormatter method.
     */
    @Test
    public void storedDateDateTimeRangeFormatterTest() {
        // Testing valid input
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formatStoredDateTimeRange("Jan 01 2024 8:00 AM", "Jan 01 2024 10:00 AM");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().formatStoredDateTimeRange("invalid", "invalid");
        assertNull(dateTime);
    }
}
