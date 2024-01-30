package nollid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import nollid.commands.ByeCommand;
import nollid.exceptions.InvalidCommandException;

public class ParserTest {
    @Test
    public void getLocalDateTimeFromString_invalidString_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.getLocalDateTimeFromString("definitely invalid string");
        });
    }

    @Test
    public void getLocalDateTimeFromString_dateOnly_success() {
        LocalDate expectedDate = LocalDate.of(2222, 2, 22);
        LocalTime expectedTime = LocalTime.of(0, 0);
        LocalDateTime result = Parser.getLocalDateTimeFromString("22/2/2222");
        assertEquals(LocalDateTime.of(expectedDate, expectedTime), result);
    }

    @Test
    public void getLocalDateTimeFromString_dateAndTime_success() {
        LocalDate expectedDate = LocalDate.of(2222, 2, 22);
        LocalTime expectedTime = LocalTime.of(12, 34);
        LocalDateTime result = Parser.getLocalDateTimeFromString("22/2/2222 12:34");
        assertEquals(LocalDateTime.of(expectedDate, expectedTime), result);
    }

    @Test
    public void parse_byeCommand_success() throws InvalidCommandException {
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertInstanceOf(ByeCommand.class, Parser.parse("ByE"));
    }

    @Test
    public void parse_noCommand_exceptionThrown() throws InvalidCommandException {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("");
        });
    }
}
