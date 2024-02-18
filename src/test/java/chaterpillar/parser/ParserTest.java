package chaterpillar.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chaterpillar.commands.ExitCommand;
import chaterpillar.commands.HelpCommand;
import chaterpillar.commands.ListAllCommand;
import chaterpillar.commands.TasksTodayCommand;
import chaterpillar.commands.UnrecognisedCommand;
import chaterpillar.exceptions.ChaterpillarException;
public class ParserTest {
    @Test
    public void parse_validInputs_success() throws ChaterpillarException {
        // correct classes being called
        assertEquals(ListAllCommand.class, Parser.parse("list").getClass());
        assertEquals(TasksTodayCommand.class, Parser.parse("today").getClass());
        assertEquals(HelpCommand.class, Parser.parse("help").getClass());
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
        assertEquals(UnrecognisedCommand.class, Parser.parse("funny text").getClass());

        // ToDO: Write Stubs for testing
    }
}
