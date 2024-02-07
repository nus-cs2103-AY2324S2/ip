package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM HHmm");

    @Test
    public void parse_byeCommand_returnsExitCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws DukeException {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommand_returnsUnmarkCommand() throws DukeException {
        Command command = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void parse_addTodoCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("todo do something");
        assertEquals(AddCommand.class, command.getClass());
    }
}