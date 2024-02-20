package parser;


import commands.Command;
import commands.ListCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseList() {
        Command c = new Parser().parse("list");
        assertEquals(c, new ListCommand());
    }
}
