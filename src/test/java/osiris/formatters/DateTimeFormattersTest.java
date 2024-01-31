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
        LocalDate date = DateTimeFormatters.getInstance().userInputDateFormatter("01-01-2024");
        assertNotNull(date);

        // Testing invalid input
        date = DateTimeFormatters.getInstance().userInputDateFormatter("invalid");
        assertNull(date);
    }

    /**
     * Test for the userInputDateTimeFormatter method.
     */
    @Test
    public void userInputDateTimeFormatterTest() {
        // Testing valid input
        LocalDateTime dateTime = DateTimeFormatters.getInstance().userInputDateTimeFormatter("01-01-2024 2359");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().userInputDateTimeFormatter("invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the userInputDateTimeRangeFormatter method.
     */
    @Test
    public void userInputDateTimeRangeFormatterTest() {
        // Testing valid input
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .userInputDateTimeRangeFormatter("01-01-2024 0000", "01-01-2024 2359");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().userInputDateTimeRangeFormatter("invalid", "invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the storedDataDateFormatter method.
     */
    @Test
    public void storedDataDateFormatterTest() {
        // Testing valid input
        LocalDate date = DateTimeFormatters.getInstance().storedDataDateFormatter("Jan 01 2024");
        assertNotNull(date);

        // Testing invalid input
        date = DateTimeFormatters.getInstance().storedDataDateFormatter("invalid");
        assertNull(date);
    }

    /**
     * Test for the storedDataDateTimeFormatter method.
     */
    @Test
    public void storedDataDateTimeFormatterTest() {
        // Testing valid input
        LocalDateTime dateTime = DateTimeFormatters.getInstance().storedDataDateTimeFormatter("Jan 01 2024 8:00 AM");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().storedDataDateTimeFormatter("invalid");
        assertNull(dateTime);
    }

    /**
     * Test for the storedDateDateTimeRangeFormatter method.
     */
    @Test
    public void storedDateDateTimeRangeFormatterTest() {
        // Testing valid input
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .storedDataDateTimeRangeFormatter("Jan 01 2024 8:00 AM", "Jan 01 2024 10:00 AM");
        assertNotNull(dateTime);

        // Testing invalid input
        dateTime = DateTimeFormatters.getInstance().storedDataDateTimeRangeFormatter("invalid", "invalid");
        assertNull(dateTime);
    }
}
