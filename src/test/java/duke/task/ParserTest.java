/*
 * ParserTest.java
 * This class contains JUnit tests for the Parser class in the Duke application.
 */

package duke.task;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;

class ParserTest {

    @Test
    void parse_validTodoCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validDeadlineCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("deadline return book /by 2022-12-12 12:00");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validEventCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("event project /from 2022-12-12 12:00 /to 2022-12-12 14:00");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validExitCommand_returnsExitCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parse_unrecognizedCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("sing a song"));
    }

    @Test
    void parse_markCommandWithNoNumber_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("marks"));
    }
}
