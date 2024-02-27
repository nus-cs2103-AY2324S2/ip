package Luna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseInvalidTest(){

        Command c;
        c = Parser.parse(" @#$@$@#$ ");
        assertEquals(Command.CommandType.INVALID, c.commandType);

        c = Parser.parse("listtt");
        assertEquals(Command.CommandType.INVALID, c.commandType);

        c = Parser.parse("todo but extra inputs");
        assertEquals(Command.CommandType.INVALID, c.commandType);


        c = Parser.parse("deadline _button_little_inputs");
        assertEquals(Command.CommandType.INVALID, c.commandType);

        c = Parser.parse("event but no dates");
        assertEquals(Command.CommandType.INVALID, c.commandType);
    }

    @Test
    public void parseListTest(){
        Command c;
        c = Parser.parse("list");
        assertEquals(Command.CommandType.LIST, c.commandType);

        c = Parser.parse("    list    ");
        assertEquals(Command.CommandType.LIST, c.commandType);


        c = Parser.parse("list 345");
        assertEquals(Command.CommandType.INVALID, c.commandType);

    }
}