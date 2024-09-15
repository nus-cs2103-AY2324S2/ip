package capone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import capone.exceptions.InvalidDateFormatException;
import capone.exceptions.InvalidTimeException;

/**
 * Test class for the Parser utility class.
 *
 * @author Tay Rui-Jie
 */
public class ParserTest {

    /**
     * Tests the behavior of parseDate when an invalid date format is provided.
     * Expects an InvalidDateException to be thrown.
     */
    @Test
    public void parseDate_invalidDate1_throwsInvalidDateException() {
        assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseDate("this is not a valid date");
        });
    }

    /**
     * Tests the behavior of parseDate when an invalid date
     * (with incorrect month and day format) is provided.
     * Expects an InvalidDateException to be thrown.
     */
    @Test
    public void parseDate_invalidDate2_throwsInvalidDateException() {
        assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseDate("2023-2-2");
        });
    }

    /**
     * Tests the behavior of parseDate with a valid date.
     * Expects the parsed LocalDate to match the expected value.
     *
     * @throws InvalidDateFormatException if the date is invalid (which is not expected in this test).
     */
    @Test
    public void parseDate_validDate_success() throws InvalidDateFormatException {
        LocalDate expectedDate = LocalDate.of(2001, 9, 26);
        LocalDate actualDate = Parser.parseDate("2001-09-26");
        assertEquals(expectedDate, actualDate);
    }

    /**
     * Tests the behavior of parseTime when an invalid time format is provided.
     * Expects an InvalidTimeException to be thrown.
     */
    @Test
    public void parseTime_invalidTime1_throwsInvalidTimeException() {
        assertThrows(InvalidTimeException.class, () -> {
            Parser.parseTime("this is not a valid time");
        });
    }

    /**
     * Tests the behavior of parseTime when an invalid time
     * (with incorrect hour) is provided.
     * Expects an InvalidTimeException to be thrown.
     */
    @Test
    public void parseTime_invalidTime2_throwsInvalidTimeException() {
        assertThrows(InvalidTimeException.class, () -> {
            Parser.parseTime("2395");
        });
    }

    /**
     * Tests the behavior of parseTime with a valid time.
     * Expects the parsed LocalTime to match the expected value.
     *
     * @throws InvalidTimeException if the time is invalid (which is not expected in this test).
     */
    @Test
    public void parseTime_validTime_success() throws InvalidTimeException {
        LocalTime expectedTime = LocalTime.of(23, 59);
        LocalTime actualTime = Parser.parseTime("2359");
        assertEquals(expectedTime, actualTime);
    }
}
