package huyang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCommand_ByeCommand_ReturnsByeCommandType() {
        assertEquals(Parser.CommandType.BYE, new Parser().parseCommand("bye"));
    }

    @Test
    public void testParseCommand_ListCommand_ReturnsListCommandType() {
        assertEquals(Parser.CommandType.LIST, new Parser().parseCommand("list"));
    }

    @Test
    public void testParseCommand_MarkCommand_ReturnsMarkCommandType() {
        assertEquals(Parser.CommandType.MARK, new Parser().parseCommand("mark 1"));
        assertEquals(Parser.CommandType.MARK, new Parser().parseCommand("maRK 1"));
    }

    @Test
    public void testParseCommand_UnmarkCommand_ReturnsUnmarkCommandType() {
        assertEquals(Parser.CommandType.UNMARK, new Parser().parseCommand("unmark 1"));
        assertEquals(Parser.CommandType.UNMARK, new Parser().parseCommand("UnMaRk 1"));
    }

    @Test
    public void testParseCommand_TodoCommand_ReturnsTodoCommandType() {
        assertEquals(Parser.CommandType.TODO, new Parser().parseCommand("todo read book"));
    }

    @Test
    public void testParseCommand_DeadlineCommand_ReturnsDeadlineCommandType() {
        assertEquals(Parser.CommandType.DEADLINE, new Parser().parseCommand("deadline return book /by 2020-12-12 1800"));
    }

    @Test
    public void testParseCommand_EventCommand_ReturnsEventCommandType() {
        assertEquals(Parser.CommandType.EVENT, new Parser().parseCommand("event project meeting /from 2020-12-12 1400 /to 2020-12-12 1600"));
    }

    @Test
    public void testParseCommand_DeleteCommand_ReturnsDeleteCommandType() {
        assertEquals(Parser.CommandType.DELETE, new Parser().parseCommand("delete 1"));
    }

    @Test
    public void testParseCommand_UnknownCommand_ReturnsUnknownCommandType() {
        assertEquals(Parser.CommandType.UNKNOWN, new Parser().parseCommand("randomcommand"));
        assertEquals(Parser.CommandType.UNKNOWN, new Parser().parseCommand("something 1"));
        assertEquals(Parser.CommandType.UNKNOWN, new Parser().parseCommand("unmarker 2"));
    }
}
