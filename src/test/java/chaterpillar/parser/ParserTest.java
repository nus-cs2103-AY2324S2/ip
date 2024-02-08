package chaterpillar.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import chaterpillar.commands.ListAllCommand;
import org.junit.jupiter.api.Test;

import chaterpillar.exceptions.ChaterpillarException;
public class ParserTest {
    @Test
    public void parse_validInputs_success() throws ChaterpillarException {
        assertEquals(new ListAllCommand(), Parser.parse("list"));
    }
}
