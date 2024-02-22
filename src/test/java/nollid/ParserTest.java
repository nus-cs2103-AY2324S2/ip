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

/**
 * A set of tests for the Parser class.
 */
public class ParserTest {
    /**
     * Tests for a DateTimeParseException thrown when attempting to parse an invalid string.
     */
    @Test
    public void getLocalDateTimeFromString_invalidString_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.getLocalDateTimeFromString("definitely invalid string");
        });
    }

    /**
     * Tests for successful creation of LocalDateTime object when only a date is supplied.
     * The default time is midnight if no time is supplied.
     */
    @Test
    public void getLocalDateTimeFromString_dateOnly_success() {
        LocalDate expectedDate = LocalDate.of(2222, 2, 22);
        LocalTime expectedTime = LocalTime.of(0, 0);
        LocalDateTime result = Parser.getLocalDateTimeFromString("22/2/2222");
        assertEquals(LocalDateTime.of(expectedDate, expectedTime), result);
    }

    /**
     * Tests for successful creation of LocalDateTime object when both date and time are supplied.
     */
    @Test
    public void getLocalDateTimeFromString_dateAndTime_success() {
        LocalDate expectedDate = LocalDate.of(2222, 2, 22);
        LocalTime expectedTime = LocalTime.of(12, 34);
        LocalDateTime result = Parser.getLocalDateTimeFromString("22/2/2222 12:34");
        assertEquals(LocalDateTime.of(expectedDate, expectedTime), result);
    }

    /**
     * Tests for the successful creation of a ByeCommand when "bye" is parsed.
     */
    @Test
    public void parse_byeCommand_success() throws InvalidCommandException {
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertInstanceOf(ByeCommand.class, Parser.parse("ByE"));
    }

    /**
     * Tests for InvalidCommandException thrown when no command is detected.
     */
    @Test
    public void parse_noCommand_exceptionThrown() throws InvalidCommandException {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("");
        });
    }

    /**
     * Tests for InvalidCommandException thrown when an invalid command is detected.
     */
    @Test
    public void parse_invalidCommand_exceptionThrown() throws InvalidCommandException {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("jdsfkjn");
        });
    }
}
