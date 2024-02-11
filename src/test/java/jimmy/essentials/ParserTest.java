package jimmy.essentials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jimmy.exceptions.JimmyException;

public class ParserTest {
    private final Parser parser = new Parser(); // Create a new Parser object.

    @Test
    public void testParseEventDetails_incorrectFormat_exceptionThrown() {
        String incorrectFormat = "A";
        Assertions.assertThrows(JimmyException.class, () -> parser.parseEventDetails(incorrectFormat));
    }

    @Test
    public void testParseEventDetails_correctFormat() throws JimmyException {
        String correctFormat = "A /from 2021-08-21 1800 /to 2021-08-21 1900";
        String[] expected = {"A", "2021-08-21 1800", "2021-08-21 1900"};
        Assertions.assertArrayEquals(expected, parser.parseEventDetails(correctFormat));
    }
}
