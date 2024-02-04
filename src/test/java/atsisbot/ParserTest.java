package atsisbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCommand() {
        assertEquals(CommandEnum.LIST, Parser.parseCommand("list"));
        assertEquals(CommandEnum.MARK, Parser.parseCommand("mark 1"));
        assertEquals(CommandEnum.UNMARK, Parser.parseCommand("unmark 1"));
        assertEquals(CommandEnum.DELETE, Parser.parseCommand("delete 1"));
        assertEquals(CommandEnum.TODO, Parser.parseCommand("todo read book"));
        assertEquals(CommandEnum.DEADLINE, Parser.parseCommand("deadline return book /by 12/12/1212 12:12"));
    }

    @Test
    public void testParseArgs() {
        assertEquals("", Parser.parseArgs("list"));
        assertEquals("1", Parser.parseArgs("mark 1"));
        assertEquals("1", Parser.parseArgs("unmark 1"));
        assertEquals("1", Parser.parseArgs("delete 1"));
        assertEquals("read book", Parser.parseArgs("todo read book"));
        assertEquals("return book /by 12/12/1212 12:12", Parser.parseArgs("deadline return book /by 12/12/1212 12:12"));
    }
}
