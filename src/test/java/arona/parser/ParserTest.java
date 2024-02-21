package arona.parser;

import arona.command.CommandType;
import arona.exception.AronaException;
import arona.exception.AronaInvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_listCommand_success() throws AronaInvalidCommandException {
        Parser parser = new Parser();
        CommandType listCommand = CommandType.LIST;
        assertEquals(parser.parseInput("lIsT"), listCommand);
    }

    @Test
    public void parseInput_invalidCommand_errorThrown() throws AronaInvalidCommandException {
        Parser parser = new Parser();
        try {
            parser.parseInput("blah");
            fail("Should have thrown invalid command error");
        } catch (AronaException error) {
            return;
        }
    }
}
