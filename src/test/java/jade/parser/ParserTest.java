package jade.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jade.exception.JadeException;


public class ParserTest {
    @Test
    public void concatStringWithTextBound() throws JadeException {
        // Start index is empty
        assertEquals("a", Parser.concatStringWithTextBound(new String[]{"read", "a", "book"}, "", "book"));
        // end index is empty
        assertEquals("book", Parser.concatStringWithTextBound(new String[]{"read", "a", "book"}, "a", ""));
        // start and end indices are both non-empty
        assertEquals("a", Parser.concatStringWithTextBound(new String[]{"read", "a", "book"}, "read", "book"));
        // start and end indices are both empty
        assertEquals("a book", Parser.concatStringWithTextBound(new String[]{"read", "a", "book"}, "", ""));
    }

    @Test
    public void parseInt_validNumber_success() throws JadeException {
        assertEquals(3, Parser.parseInt("3"));
        assertEquals(0, Parser.parseInt("0"));
    }

    @Test
    public void parseInt_invalidNumber_exceptionThrown() {
        try {
            assertEquals(1, Parser.parseInt("1a"));
            fail();
        } catch (JadeException e) {
            assertEquals("Please input a valid number.", e.getMessage());
        }
    }
}
