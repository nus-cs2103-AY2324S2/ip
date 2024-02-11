package tyler.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tyler.command.Command;
import tyler.command.ExitCommand;
import tyler.exception.TylerException;

public class ParserTest {
    @Test
    public void testBye() throws TylerException {
        Command bye = new ExitCommand();
        assertEquals(true, Parser.parse("bye") instanceof ExitCommand);
    }

}
