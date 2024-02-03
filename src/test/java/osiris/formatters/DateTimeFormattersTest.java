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
     * Test case to validate the formatting of user input date strings into LocalDate objects.
     */
    @Test
    public void formatUserInputDate_validInput_localDateReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatUserInputDate("01-01-2024");
        assertNotNull(date);
    }

    /**
     * Test case to validate handling of invalid user input date strings, expecting a null result.
     */
    @Test
    public void formatUserInputDate_invalidInput_nullReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatUserInputDate("invalid");
        assertNull(date);
    }

    /**
     * Test case to validate the formatting of user input date-time strings into LocalDateTime objects.
     */
    @Test
    public void formatUserInputDateTime_validInput_localDateTimeReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("01-01-2024 2359");
        assertNotNull(dateTime);
    }

    /**
     * Test case to validate handling of invalid user input date-time strings, expecting a null result.
     */
    @Test
    public void formatUserInputDateTime_invalidInput_nullReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("invalid");
        assertNull(dateTime);
    }

    /**
     * Test case to validate the formatting of user input date-time range strings.
     */
    @Test
    public void formatUserInputDateTimeRange_validInput_localDateTimeArrReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formatUserInputDateTimeRange("01-01-2024 0000", "01-01-2024 2359");
        assertNotNull(dateTime);
    }

    /**
     * Test case to validate handling of invalid user input date-time range strings, expecting a null result.
     */
    @Test
    public void formatUserInputDateTimeRange_invalidInput_nullReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance().formatUserInputDateTimeRange("invalid", "invalid");
        assertNull(dateTime);
    }

    /**
     * Test case to validate the formatting of stored date strings into LocalDate objects.
     */
    @Test
    public void formatStoredDate_validInput_localDateReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatStoredDate("Jan 01 2024");
        assertNotNull(date);
    }

    /**
     * Test case to validate handling of invalid stored date strings, expecting a null result.
     */
    @Test
    public void formatStoredDate_invalidInput_nullReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatStoredDate("invalid");
        assertNull(date);
    }

    /**
     * Test case to validate the formatting of stored date-time strings into LocalDateTime objects.
     */
    @Test
    public void formatStoredDateTime_validInput_localDateTimeReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance()
                .formatStoredDateTime("Jan 01 2024 8:00 AM");
        assertNotNull(dateTime);
    }

    /**
     * Test case to validate handling of invalid stored date-time strings, expecting a null result.
     */
    @Test
    public void formatStoredDateTime_invalidInput_nullReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatStoredDateTime("invalid");
        assertNull(dateTime);
    }

    /**
     * Test case to validate the formatting of stored date-time range strings into an array of LocalDateTime objects.
     */
    @Test
    public void formatStoredDateTimeRange_validInput_localDateTimeReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formatStoredDateTimeRange("Jan 01 2024 8:00 AM", "Jan 01 2024 10:00 AM");
        assertNotNull(dateTime);
    }

    /**
     * Test case to validate handling of invalid stored date-time range strings, expecting a null result.
     */
    @Test
    public void formatStoredDateTimeRange_invalidInput_nullReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance().formatStoredDateTimeRange("invalid", "invalid");
        assertNull(dateTime);
    }
}
