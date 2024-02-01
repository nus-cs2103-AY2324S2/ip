package Jerry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void parse_ByeCommand_ReturnsByeCommand() {
        Command command = parser.parse("bye");
        assertEquals(Command.CommandType.BYE, command.getCommandType());
    }

    @Test
    void parse_ListCommand_ReturnsListCommand() {
        Command command = parser.parse("list");
        assertEquals(Command.CommandType.LIST, command.getCommandType());
    }

    @Test
    void parse_MarkCommandWithIndex_ReturnsMarkCommandAndIndex() {
        Command command = parser.parse("mark 1");
        assertEquals(Command.CommandType.MARK, command.getCommandType());
        assertEquals(0, command.getTaskIndex()); // Assuming taskIndex is adjusted to 0-based index
    }

    @Test
    void parse_UnmarkCommandWithIndex_ReturnsUnmarkCommandAndIndex() {
        Command command = parser.parse("unmark 2");
        assertEquals(Command.CommandType.UNMARK, command.getCommandType());
        assertEquals(1, command.getTaskIndex());
    }

    @Test
    void parse_DeleteCommandWithIndex_ReturnsDeleteCommandAndIndex() {
        Command command = parser.parse("delete 3");
        assertEquals(Command.CommandType.DELETE, command.getCommandType());
        assertEquals(2, command.getTaskIndex());
    }

    @Test
    void parse_TodoCommandWithDescription_ReturnsAddTodoCommandAndDescription() {
        Command command = parser.parse("todo read book");
        assertEquals(Command.CommandType.ADD_TODO, command.getCommandType());
        assertEquals("read book", command.getParts());
    }

    // Add tests for ADD_DEADLINE, ADD_EVENT, and INVALID command types similarly

}