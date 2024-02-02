package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seiki.commands.Command;
import seiki.commands.ListCommand;
import seiki.data.exception.SeikiException;
import seiki.parser.Parser;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testEmptyInputThrowsSeikiException() {
        String[] emptyInputs = {"", " ", "\n \n"};
        for (String inp : emptyInputs) {
            Exception exception = assertThrows(SeikiException.class, () -> parser.parse(inp));
            assertEquals("I'm sorry, I didn't quite understand that.", exception.getMessage());
        }
    }

    @Test
    public void testUnknownInputThrowsSeikiException() {
        try {
            Command cmd = parser.parse("unknowncommandword arguments arguments");
        } catch (Exception e) {
            assertEquals("I'm sorry, I didn't quite understand that.", e.getMessage());
        }
    }

    @Test
    public void parse_listCommand_parsedCorrectly() throws SeikiException {
        Command result = parser.parse("list");
        assertEquals(result.getClass(), ListCommand.class);
    }

}
