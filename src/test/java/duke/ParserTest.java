package duke;//same package as the class being tested

import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static duke.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() throws NoCmdException {
        Command ListCommand = new ListCommand();
        Command testCommand = parse("list");
        assertEquals(ListCommand.getClass(), testCommand.getClass());
    }
}
