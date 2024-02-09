package missminutes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseCommand_byeCommand_returnsByeCommandType() {
        String command = "bye";
        Parser.CommandType commandType = Parser.parseCommand(command);

        assertEquals(Parser.CommandType.BYE, commandType);
    }

    @Test
    public void testParseCommand_listCommand_returnsListCommandType() {
        String command = "list";
        Parser.CommandType commandType = Parser.parseCommand(command);

        assertEquals(Parser.CommandType.LIST, commandType);
    }

    @Test
    public void testParseCommand_markCommand_returnsMarkCommandType() {
        String command1 = "mark 1";
        Parser.CommandType commandType1 = Parser.parseCommand(command1);
        assertEquals(Parser.CommandType.MARK, commandType1);

        String command2 = "maRK 1";
        Parser.CommandType commandType2 = Parser.parseCommand(command2);
        assertEquals(Parser.CommandType.MARK, commandType2);
    }

    @Test
    public void testParseCommand_unmarkCommand_returnsUnmarkCommandType() {
        String command1 = "unmark 1";
        Parser.CommandType commandType1 = Parser.parseCommand(command1);
        assertEquals(Parser.CommandType.UNMARK, commandType1);

        String command2 = "UnMaRk 1";
        Parser.CommandType commandType2 = Parser.parseCommand(command2);
        assertEquals(Parser.CommandType.UNMARK, commandType2);
    }

    @Test
    public void testParseCommand_unknownCommand_returnsUnknownCommandType() {
        String command1 = "randomcommand";
        Parser.CommandType commandType1 = Parser.parseCommand(command1);
        assertEquals(Parser.CommandType.UNKNOWN, commandType1);

        String command2 = "markiplier 1";
        Parser.CommandType commandType2 = Parser.parseCommand(command2);
        assertEquals(Parser.CommandType.UNKNOWN, commandType2);

        String command3 = "unmarker 2";
        Parser.CommandType commandType3 = Parser.parseCommand(command3);
        assertEquals(Parser.CommandType.UNKNOWN, commandType3);
    }
}

