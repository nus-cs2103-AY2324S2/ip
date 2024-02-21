package mona;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void testGetCommand() throws MonaException {
        Parser parser = new Parser();
        assertEquals(Mona.Command.BYE, parser.getCurrentCommand(new String[]{"bye"}));
        assertEquals(Mona.Command.TODO, parser.getCurrentCommand(new String[]{"todo"}));
        assertEquals(Mona.Command.EVENT, parser.getCurrentCommand(new String[]{"event"}));
        assertEquals(Mona.Command.DEADLINE, parser.getCurrentCommand(new String[]{"deadline"}));
        assertEquals(Mona.Command.LIST, parser.getCurrentCommand(new String[]{"list"}));
        assertEquals(Mona.Command.UNMARK, parser.getCurrentCommand(new String[]{"unmark"}));
        assertEquals(Mona.Command.MARK, parser.getCurrentCommand(new String[]{"mark"}));
        assertEquals(Mona.Command.UPDATE, parser.getCurrentCommand(new String[]{"update"}));
    }
}
