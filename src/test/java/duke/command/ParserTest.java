package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParseByeCommand() throws DukeException {
        Object[] expected = { "bye" };
        assertArrayEquals(expected, Parser.parseCommand("bye"));
    }
    @Test
    public void testParseListCommand() throws DukeException {
        Object[] expected = { "list" };
        assertArrayEquals(expected, Parser.parseCommand("list"));
    }

    @Test
    public void testParseTodoCommand() throws DukeException {
        Object[] expected = { "todo", "description" };
        assertArrayEquals(expected, Parser.parseCommand("todo description"));
    }

    @Test
    public void testParseDeadlineCommand() throws DukeException {
        Object[] expected = { "deadline", "description", LocalDate.parse("2024-02-10") };
        assertArrayEquals(expected, Parser.parseCommand("deadline description /by 2024-02-10"));
    }

    @Test
    public void testParseEventCommand() throws DukeException {
        Object[] expected = { "event", "description", LocalDate.parse("2024-02-10"), "10:00", LocalDate.parse("2024-02-12"), "12:00" };
        assertArrayEquals(expected, Parser.parseCommand("event description /from 2024-02-10 10:00 /to 2024-02-12 12:00"));
    }

    @Test
    public void testParseMarkCommand() throws DukeException {
        Object[] expected = { "mark", 1 };
        assertArrayEquals(expected, Parser.parseCommand("mark 2"));
    }

    @Test
    public void testParseDeleteCommand() throws DukeException {
        Object[] expected = { "delete", 1 };
        assertArrayEquals(expected, Parser.parseCommand("delete 1"));
    }

    @Test
    public void testParseCommandInvalid() {
        assertThrows(DukeException.class, () -> Parser.parseCommand("invalid command"));
    }

}
