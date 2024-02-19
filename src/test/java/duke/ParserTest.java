package duke;

import xilef.XilefException;
import xilef.Parser;
import xilef.command.AddCommand;
import xilef.command.DeleteCommand;
import xilef.command.ExitCommand;
import xilef.command.ListCommand;
import xilef.command.MarkCommand;
import xilef.command.UnmarkCommand;
import xilef.command.Command;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM HHmm");

    @Test
    public void parse_byeCommand_returnsExitCommand() throws XilefException {
        Command command = Parser.parse("bye");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws XilefException {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws XilefException {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommand_returnsUnmarkCommand() throws XilefException {
        Command command = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws XilefException {
        Command command = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void parse_addTodoCommand_returnsAddCommand() throws XilefException {
        Command command = Parser.parse("todo do something");
        assertEquals(AddCommand.class, command.getClass());
    }
}