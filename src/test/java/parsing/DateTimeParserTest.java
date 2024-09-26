package parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


/**
 * Test class for DateTimeParser.
 */
public class DateTimeParserTest {

    /**
     * Test for successful parsing of date and time.
     */
    @Test
    public void parseDateTimeTest() {
        assertEquals(LocalDateTime.of(2024, 2, 3, 18, 0),
                new DateTimeParser().parseDateTime("03-02-2024 1800"));
    }

    /**
     * Test for parsing failure with incorrect date-time format.
     */
    @Test
    public void failParseDateTimeTest() {
        assertThrows(DateTimeParseException.class, () -> {
            new DateTimeParser().parseDateTime("03/02/2024 1800");
        });
    }
}
