package fluffy.parser;

import static fluffy.parser.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fluffy.FluffyException;
import fluffy.command.Command;
import fluffy.command.EventCommand;

public class ParserTest {

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(FluffyException.class, () -> parse("invalid"));
    }

    @Test
    public void parseEventCommand_emptyDescription_exceptionThrown() {
        String[] commandParts = new String[]{"event"};
        assertThrows(FluffyException.class, () -> Parser.parseEventCommand(commandParts));
    }

    @Test
    public void parseEventCommand_noStartDate_exceptionThrown() {
        String[] commandParts = new String[]{"event", "description"};
        assertThrows(FluffyException.class, () -> Parser.parseEventCommand(commandParts));
    }

    @Test
    public void parseEventCommand_noEndDate_exceptionThrown() {
        String[] commandParts = new String[]{"event", "description /from 2022-01-01"};
        assertThrows(FluffyException.class, () -> Parser.parseEventCommand(commandParts));
    }

    @Test
    public void parseEventCommand_invalidDateFormat_exceptionThrown() {
        String[] commandParts = new String[]{"event", "description /from 01-01-2022 /to 01-01-2023"};
        assertThrows(FluffyException.class, () -> Parser.parseEventCommand(commandParts));
    }

    @Test
    public void parseEventCommand_validCommand_eventCommandReturned() throws FluffyException {
        String[] commandParts = new String[]{"event", "description /from 2022-01-01 /to 2023-01-01"};
        Command actual = Parser.parseEventCommand(commandParts);
        assertInstanceOf(EventCommand.class, actual);
    }
}
