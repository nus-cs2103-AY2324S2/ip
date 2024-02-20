package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void nextUntilOption_normalInput_success() throws DukeOptionParsingException {
        Parser p = new Parser("test /op test");
        assertEquals("test", p.nextUntilOption());
        assertEquals("/op test", p.rest());
    }

    @Test
    public void nextUntilOption_noOption_wholeString() throws DukeOptionParsingException {
        Parser p = new Parser("a b c d");
        assertEquals(p.nextUntilOption(), "a b c d");
    }

    @Test
    public void nextUntilOption_reachedOption_emptyString() throws DukeOptionParsingException {
        Parser p = new Parser("pop /by this");
        p.next();
        assertEquals(p.nextUntilOption(), "");
    }

    @Test
    public void nextUntilOption_endInput_throws() throws DukeOptionParsingException {
        Parser p = new Parser("test");
        p.next();
        assertThrows(DukeOptionParsingException.class, p::nextUntilOption);
    }

    @Test
    public void assertNext_different_throws() {
        Parser p = new Parser("abc");
        assertThrows(DukeOptionParsingException.class, () -> p.assertNext("xyz"));
    }

    @Test
    public void assertNext_same_success() throws DukeException {
        Parser p = new Parser("abc");
        p.assertNext("abc");
    }

    @Test
    public void assertNext_twoTokens_success() throws DukeException {
        Parser p = new Parser("abc xyz");
        p.assertNext("abc");
        p.assertNext("xyz");
    }
}
