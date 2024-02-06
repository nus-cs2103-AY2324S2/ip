package osiris.formatters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import osiris.exceptions.OsirisException;

/**
 * Unit tests for the DateTimeFormatters class.
 */
public class DateTimeFormattersTest {

    /**
     * Tests to validate the formatting of user input date strings into LocalDate objects.
     */
    @Test
    public void formatUserInputDate_validInput_localDateReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatUserInputDate("01-01-2024");
        assertNotNull(date);
    }

    /**
     * Tests to validate handling of invalid user input date strings, expecting an exception.
     */
    @Test
    public void formatUserInputDate_invalidInput_throwException() {
        try {
            LocalDate date = DateTimeFormatters.getInstance().formatUserInputDate("invalid");
        } catch (OsirisException e) {
            assertEquals("Failed to parse the date-time string: invalid"
                    + "\nPlease try /by dd-mm-yyyy for a deadline tasks.", e.getMessage());
        }
    }

    /**
     * Tests to validate the formatting of user input date-time strings into LocalDateTime objects.
     */
    @Test
    public void formatUserInputDateTime_validInput_localDateTimeReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("01-01-2024 2359");
        assertNotNull(dateTime);
    }

    /**
     * Tests to validate handling of invalid user input date-time strings, expecting an exception.
     */
    @Test
    public void formatUserInputDateTime_invalidInput_throwException() {
        try {
            LocalDateTime dateTime = DateTimeFormatters.getInstance().formatUserInputDateTime("invalid");
        } catch (OsirisException e) {
            assertEquals("Failed to parse the date-time string: invalid"
                    + "\nPlease provide date time range 'dd-MM-yyyy HHmm' format.", e.getMessage());
        }
    }

    /**
     * Tests to validate the formatting of user input date-time range strings.
     */
    @Test
    public void formatUserInputDateTimeRange_validInput_localDateTimeArrReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formatUserInputDateTimeRange("01-01-2024 0000", "01-01-2024 2359");
        assertNotNull(dateTime);
    }

    /**
     * Tests to validate handling of invalid user input date-time range strings, expecting an exception.
     */
    @Test
    public void formatUserInputDateTimeRange_invalidInput_throwException() {
        try {
            LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                    .formatUserInputDateTimeRange("invalid", "invalid");
        } catch (OsirisException e) {
            assertEquals("Failed to parse the date time range."
                    + "\nPlease provide date time range 'dd-MM-yyyy HHmm' format.", e.getMessage());
        }
    }

    /**
     * Tests to validate the formatting of stored date strings into LocalDate objects.
     */
    @Test
    public void formatStoredDate_validInput_localDateReturned() {
        LocalDate date = DateTimeFormatters.getInstance().formatStoredDate("Jan 01 2024");
        assertNotNull(date);
    }

    /**
     * Tests to validate handling of invalid stored date strings, expecting an exception.
     */
    @Test
    public void formatStoredDate_invalidInput_throwException() {
        try {
            LocalDate date = DateTimeFormatters.getInstance().formatStoredDate("invalid");
        } catch (OsirisException e) {
            assertEquals("Unable to parse Date Times stored. "
                    + "Storage File may have been corrupted.", e.getMessage());
        }
    }

    /**
     * Tests to validate the formatting of stored date-time strings into LocalDateTime objects.
     */
    @Test
    public void formatStoredDateTime_validInput_localDateTimeReturned() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance()
                .formatStoredDateTime("Jan 01 2024 8:00 AM");
        assertNotNull(dateTime);
    }

    /**
     * Tests to validate handling of invalid stored date-time strings, expecting an exception.
     */
    @Test
    public void formatStoredDateTime_invalidInput_throwException() {
        try {
            LocalDateTime dateTime = DateTimeFormatters.getInstance().formatStoredDateTime("invalid");
        } catch (OsirisException e) {
            assertEquals("Unable to parse Date Times stored. "
                    + "Storage File may have been corrupted.", e.getMessage());
        }
    }

    /**
     * Tests to validate the formatting of stored date-time range strings into an array of LocalDateTime objects.
     */
    @Test
    public void formatStoredDateTimeRange_validInput_localDateTimeReturned() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .formatStoredDateTimeRange("Jan 01 2024 8:00 AM", "Jan 01 2024 10:00 AM");
        assertNotNull(dateTime);
    }

    /**
     * Tests to validate handling of invalid stored date-time range strings, expecting an exception.
     */
    @Test
    public void formatStoredDateTimeRange_invalidInput_nullReturned() {
        try {
            LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                    .formatStoredDateTimeRange("invalid", "invalid");
        } catch (OsirisException e) {
            assertEquals("Unable to parse Date Times stored. "
                    + "Storage File may have been corrupted.", e.getMessage());
        }
    }
}
