package Tests;

import org.junit.jupiter.api.Test;

import command.ToDoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import emis.Parser;
import exceptions.EmisException;

/**
 * The ParserTest class contains JUnit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests the parse method of the Parser class for successful parsing.
     */
    @Test
    public void parse_success() {
        try {
            assertTrue(Parser.parse("todo test1") instanceof ToDoCommand);
        } catch (EmisException e) {
            fail("Unexpected EmisException: " + e.getMessage());
        }
    }

    /**
     * Tests the parse method of the Parser class for failed parsing.
     */
    @Test
    public void parse_fail() {
        try {
            Parser.parse("screaming crying");
            fail("Expected EmisException to be thrown"); // the test should not reach this line
        } catch (EmisException e) {
            assertEquals("Meow? Emis doesn't understand.\n Please send 'help' if needed.", e.getMessage());
        }
    }
}
