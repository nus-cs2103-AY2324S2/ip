package datesandtimes;
import datesandtimes.DateTimeParser;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeParserTest {
    @Test
    public void dateCorrectTest() {
        assertEquals("Aug 21 2019", DateTimeParser.parseDate(LocalDate.parse("2019-08-21")));

        assertEquals("Aug 21 2017", DateTimeParser.parseDate(LocalDate.parse("2017-08-21")));

        assertEquals("May 23 2045", DateTimeParser.parseDate(LocalDate.parse("2045-05-23")));
    }

    @Test
    public void dateExceptionTest() {
        //Check invalid year
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.parseDate(LocalDate.parse("20455-01-21")));

        //Check invalid date
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.parseDate(LocalDate.parse("2045-0-21")));

        //Check invalid day
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.parseDate(LocalDate.parse("2045-0-32")));

        //Check mix of invalids
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.parseDate(LocalDate.parse("20455-0-32")));

        //Check logical date is wrong, no feb 31th (yes I know its wrong intelliJ)
        assertThrows(DateTimeException.class,
                () -> DateTimeParser.parseDate(LocalDate.parse("2045-02-31")));
    }
}
