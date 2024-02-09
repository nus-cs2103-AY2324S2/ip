package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void parseDate_correctFormat_success() {
        String input1 = "09/02/2024 1800";
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 9, 18,00);
        LocalDateTime testDate = Parser.parseDate(input1);
        assertEquals(testDate, expectedDate);
    }
}
