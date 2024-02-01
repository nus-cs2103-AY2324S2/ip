package Dook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testThrowsDookException() {
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
    }
}