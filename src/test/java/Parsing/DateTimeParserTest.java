package Parsing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeParserTest {

    @Test
    public void parseDateTimeTest(){
        assertEquals(LocalDateTime.of(2024, 2, 3, 18, 0), new DateTimeParser().parseDateTime("03-02-2024 1800"));
    }

    @Test
    public void failParseDateTimeTest(){
        assertThrows(DateTimeParseException.class, () -> {
            new DateTimeParser().parseDateTime("03/02/2024 1800");
        });
    }
}
