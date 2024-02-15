package nicky.task;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nicky.NickyException;
import nicky.command.AddCommand;
import nicky.command.Command;
import nicky.command.ExitCommand;

/**
 * This class contains JUnit tests for the Parser class in the Nicky application.
 */
class ParserTest {

    @Test
    void parse_validTodoCommand_returnsAddCommand() throws NickyException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validDeadlineCommand_returnsAddCommand() throws NickyException {
        Command command = Parser.parse("deadline return book /by 2022-12-12 12:00");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validEventCommand_returnsAddCommand() throws NickyException {
        Command command = Parser.parse("event project /from 2022-12-12 12:00 /to 2022-12-12 14:00");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validExitCommand_returnsExitCommand() throws NickyException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parse_unrecognizedCommand_throwsNickyException() {
        assertThrows(NickyException.class, () -> Parser.parse("sing a song"));
    }

    @Test
    void parse_markCommandWithNoNumber_throwsNickyException() {
        assertThrows(NickyException.class, () -> Parser.parse("marks"));
    }
}
