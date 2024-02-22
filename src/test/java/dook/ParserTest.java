package dook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParseThrowsDookException() {
        boolean isThrown = false;
        try {
            Parser.parse("laklflaelflal");
        } catch (DookException e) {
            isThrown = true;
        }
        assertTrue(isThrown);
        isThrown = false;
        try {
            Parser.parse("E | yes | a ");
        } catch (DookException e) {
            isThrown = true;
        }
        assertTrue(isThrown);
        try {
            Parser.parse("A | X | by: 2024-12-24 2359");
        } catch (DookException e) {
            isThrown = true;
        }
        assertTrue(isThrown);
        try {
            Parser.parse(" ");
        } catch (DookException e) {
            isThrown = true;
        }
        assertTrue(isThrown);
    }
}