package Parsing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    @Test
    public void parseDateTimeTest(){
        assertEquals(new DateTimeParser().parseDateTime("03-02-2024 1800"), new DateTimeParser().parseDateTime("03-02-2024 1800"));
    }

    @Test
    public void anotherParseDateTimeTest(){
        assertEquals(new DateTimeParser().parseDateTime("03-02-2024 1800"), new DateTimeParser().parseDateTime("03-02-2024 1800"));
    }
}
