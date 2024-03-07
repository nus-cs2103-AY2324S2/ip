package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseDueTime_dateTimeString_success() {
        assertEquals(LocalDateTime.parse("2022-12-03T18:00:00"),
                new Parser("deadline task /by 2022-12-03 18:00").parseDueTime());

        assertEquals(LocalDateTime.parse("2023-10-03T05:00:00"),
                new Parser("deadline other task /by 2023-10-03 05:00").parseDueTime());
    }

    @Test
    public void parseDueTime_wrongFormatDateTimeString_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class,
                () -> new Parser("deadline task /by 2022-12-0318:00").parseDueTime());

        assertThrows(DateTimeParseException.class,
                () -> new Parser("deadline other task /by 2023/10/03 0500").parseDueTime());
    }

    @Test
    public void parseDueTime_emptyDueTime_failExceptionThrown() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new Parser("deadline task /by ").parseDueTime());
    }
}
