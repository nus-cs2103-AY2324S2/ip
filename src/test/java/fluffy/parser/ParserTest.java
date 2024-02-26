package fluffy.parser;

import static fluffy.parser.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fluffy.FluffyException;
import fluffy.command.ByeCommand;
import fluffy.command.Command;
import fluffy.command.DeadlineCommand;
import fluffy.command.DeleteCommand;
import fluffy.command.EventCommand;
import fluffy.command.ListCommand;
import fluffy.command.MarkCommand;
import fluffy.command.TodoCommand;
import fluffy.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(FluffyException.class, () -> parse("invalid"));
    }

    @Test
    public void parse_todoCommand_todoCommandReturned() throws FluffyException {
        Command command = parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parse_deadlineCommand_deadlineCommandReturned() throws FluffyException {
        Command command = parse("deadline return book /by 2024-04-25");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void parse_eventCommand_eventCommandReturned() throws FluffyException {
        Command command = parse("event project meeting /from 2024-09-25 /to 2024-09-26");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void parse_listCommand_listCommandReturned() throws FluffyException {
        Command command = parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_deleteCommand_deleteCommandReturned() throws FluffyException {
        Command command = parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void parse_markCommand_markCommandReturned() throws FluffyException {
        Command command = parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parse_unmarkCommand_unmarkCommandReturned() throws FluffyException {
        Command command = parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void parse_byeCommand_byeCommandReturned() throws FluffyException {
        Command command = parse("bye");
        assertInstanceOf(ByeCommand.class, command);
    }
}
