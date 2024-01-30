package osiris.formatters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeFormattersTest {

    @Test
    public void userInputDateFormatterTest() {
        LocalDate date = DateTimeFormatters.getInstance().userInputDateFormatter("01-01-2024");
        assertNotNull(date);
        date = DateTimeFormatters.getInstance().userInputDateFormatter("invalid");
        assertNull(date);
    }
    @Test
    public void userInputDateTimeFormatterTest() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().userInputDateTimeFormatter("01-01-2024 2359");
        assertNotNull(dateTime);
        dateTime = DateTimeFormatters.getInstance().userInputDateTimeFormatter("invalid");
        assertNull(dateTime);
    }

    @Test
    public void userInputDateTimeRangeFormatterTest() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .userInputDateTimeRangeFormatter("01-01-2024 0000", "01-01-2024 2359");
        assertNotNull(dateTime);
        dateTime = DateTimeFormatters.getInstance().userInputDateTimeRangeFormatter("invalid", "invalid");
        assertNull(dateTime);
    }

    @Test
    public void storedDataDateFormatterTest() {
        LocalDate date = DateTimeFormatters.getInstance().storedDataDateFormatter("Jan 01 2024");
        assertNotNull(date);
        date = DateTimeFormatters.getInstance().storedDataDateFormatter("invalid");
        assertNull(date);
    }
    @Test
    public void storedDataDateTimeFormatterTest() {
        LocalDateTime dateTime = DateTimeFormatters.getInstance().storedDataDateTimeFormatter("Jan 01 2024 8:00 AM");
        assertNotNull(dateTime);
        dateTime = DateTimeFormatters.getInstance().storedDataDateTimeFormatter("invalid");
        assertNull(dateTime);
    }

    @Test
    public void storedDateDateTimeRangeFormatterTest() {
        LocalDateTime[] dateTime = DateTimeFormatters.getInstance()
                .storedDataDateTimeRangeFormatter("Jan 01 2024 8:00 AM", "Jan 01 2024 10:00 AM");
        assertNotNull(dateTime);
        dateTime = DateTimeFormatters.getInstance().storedDataDateTimeRangeFormatter("invalid", "invalid");
        assertNull(dateTime);
    }


}
