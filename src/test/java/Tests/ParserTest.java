package Tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import emisExceptions.EmisException;
import emis.Parser;
import emisCommand.ToDoCommand;

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
            assertEquals("Invalid command!", e.getMessage());
        }
    }
}
