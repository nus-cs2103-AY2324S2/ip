package yue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


import yue.command.Command;
import yue.command.TodoCommand;
public class ParserTest {

    @Test
    public void testParse_ValidInput() throws YueException {
        Command command = Parser.parse("todo Buy groceries");

        assertEquals(TodoCommand.class, command.getClass());
    }

    @Test
    public void testParse_NullInput() {
        assertThrows(YueException.class, () -> {
            Parser.parse(null);
        });
    }

    @Test
    public void testParse_BlankInput() {
        assertThrows(YueException.class, () -> {
            Parser.parse("");
        });
    }

    @Test
    public void testParse_UnknownCommand() {
        assertThrows(YueException.class, () -> {
            Parser.parse("invalid command");
        });
    }
}

