package capone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import capone.exceptions.InvalidDateException;
import capone.exceptions.InvalidTimeException;

public class ParserTest {
    @Test
    public void parseDate_invalidDate1_exceptionThrown() {
        assertThrows(InvalidDateException.class, () -> {
            Parser.parseDate("this is not a valid date");
        });
    }

    @Test
    public void parseDate_invalidDate2_exceptionThrown() {
        assertThrows(InvalidDateException.class, () -> {
            Parser.parseDate("2023-2-2");
        });
    }

    @Test
    public void parseDate_validDatesuccess() throws InvalidDateException {
        LocalDate expectedDate = LocalDate.of(2001, 9, 26);
        LocalDate actualDate = Parser.parseDate("2001-09-26");
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void parseTime_invalidTime1_exceptionThrown() {
        assertThrows(InvalidTimeException.class, () -> {
            Parser.parseTime("this is not a valid time");
        });
    }

    @Test
    public void parseTime_invalidTime2_exceptionThrown() {
        assertThrows(InvalidTimeException.class, () -> {
            Parser.parseTime("2395");
        });
    }

    @Test
    public void parseTime_validTime_success() throws InvalidTimeException {
        LocalTime expectedTime = LocalTime.of(23, 59);
        LocalTime actualTime = Parser.parseTime("2359");
        assertEquals(expectedTime, actualTime);
    }
}
