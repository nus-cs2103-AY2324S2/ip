package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDueTime_dateTimeString_success() {
        assertEquals(LocalDateTime.parse("2022-12-03T18:00:00"),
                new Parser("task /by 2022-12-03 18:00", "deadline").parseDueTime());

        assertEquals(LocalDateTime.parse("2023-10-03T05:00:00"),
                new Parser("other task /by 2023-10-03 05:00", "deadline").parseDueTime());
    }

    @Test
    public void parseDueTime_wrongFormatDateTimeString_exceptionThrown() {
        try {
            new Parser("task /by 2022-12-0318:00", "deadline").parseDueTime();
        } catch (DateTimeParseException e) {
            assertEquals("Text '2022-12-0318:00' could not be parsed at index 10", e.getMessage());
        }

        try {
            new Parser("other task /by 2023/10/03 0500", "deadline").parseDueTime();
        } catch (DateTimeParseException e) {
            assertEquals("Text '2023/10/03 0500' could not be parsed at index 4", e.getMessage());
        }
    }
}
