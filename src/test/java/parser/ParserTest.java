package parser;

import exception.IncompleteCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void extractCommand_twoWordInput_returnsExtractedCommand() {
        String string = "todo something";
        String expected = "todo";
        Parser parser = new Parser();
        assertEquals(expected, parser.extractCommand(string));
    }

    @Test
    public void extractCommand_multipleWordInput_returnsExtractedCommand(){
        String string = "deadline return book /by Sunday";
        String expected = "deadline";
        Parser parser = new Parser();
        assertEquals(expected, parser.extractCommand(string));
    }

    @Test
    public void extractDescription_validInput_returnsExtractedDescription() throws IncompleteCommandException {
        String string = "deadline return book /by Sunday";
        String expected = "return book /by Sunday";
        Parser parser = new Parser();
        assertEquals(expected, parser.extractDescription(string));
    }

    @Test
    public void extractDescription_incompleteInput_returnsExtractedDescription() {
        String string = "deadline";
        Parser parser = new Parser();
        assertThrows(IncompleteCommandException.class, () -> parser.extractDescription(string));
    }
}