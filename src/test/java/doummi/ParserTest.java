package doummi;//same package as the class being tested

import doummi.command.Command;
import doummi.command.ListCommand;
import org.junit.jupiter.api.Test;

import static doummi.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() throws NoCmdException {
        Command ListCommand = new ListCommand();
        Command testCommand = parse("list");
        assertEquals(ListCommand.getClass(), testCommand.getClass());
    }
}
