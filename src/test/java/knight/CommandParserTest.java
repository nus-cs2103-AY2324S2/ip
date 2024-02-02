package knight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    @Test
    public void parseTest1() {
        assertEquals(Command.TODO, CommandParser.parseCommand("todo read book"));
    }
}
