/*
 * ParserTest.java
 * This class contains JUnit tests for the Parser class in the Duke application.
 */

package duke.task;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ParserTest {

    @Test
    void parse_validTodoCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    void parse_validDeadlineCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("deadline return book /by 2022-12-12T12:00");
        assertInstanceOf(AddCommand.class, command);
    }
}
