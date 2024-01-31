package slaybot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



public class ParserTest {
    @Test
    public void testDateTimeParser() {
        Parser parser = new Parser();
        LocalDateTime testDateTime = parser.parseDateTime("2/12/2019 1800");
        int year = 2019;
        int month = 2;
        int day = 12;
        int hour = 18;
        int minute = 0;

        LocalDateTime actualDateTime = LocalDateTime.of(year, month, day, hour, minute);
        assertEquals(testDateTime, actualDateTime);
    }
}
