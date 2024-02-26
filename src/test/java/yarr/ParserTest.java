package yarr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDate_correctFormat_format1_success() {
        String input1 = "09/02/2024 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 9, 18, 00);
        LocalDateTime testDate = Parser.parseDate(input1);
        assertEquals(testDate, expectedDate);
    }
    @Test
    public void parseDate_correctFormat_format2_success() {
        String input1 = "09-02-2024 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 9, 18, 00);
        LocalDateTime testDate = Parser.parseDate(input1);
        assertEquals(testDate, expectedDate);
    }
    @Test
    public void parseDate_correctFormat_format3_success() {
        String input1 = "09022024 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 9, 18, 00);
        LocalDateTime testDate = Parser.parseDate(input1);
        assertEquals(testDate, expectedDate);
    }
}
