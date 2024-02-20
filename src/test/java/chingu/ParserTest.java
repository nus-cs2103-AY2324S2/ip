package chingu;//same package as the class being tested

import chingu.command.Command;
import chingu.command.ListCommand;
import chingu.exception.NoCommandException;
import org.junit.jupiter.api.Test;

import static chingu.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() throws NoCommandException {
        Command ListCommand = new ListCommand();
        Command testCommand = parse("list");
        assertEquals(ListCommand.getClass(), testCommand.getClass());
    }
}
