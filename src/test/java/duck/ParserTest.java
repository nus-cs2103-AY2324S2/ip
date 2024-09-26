package duck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Parser class, which is responsible for parsing user input and generating corresponding commands.
 */
public class ParserTest {

    /**
     * Tests the parseCommand method for the "bye" input, expecting an ExitCommand instance.
     *
     * @throws DukeException If an error occurs during the test.
     */
    @Test
    public void testParseCommandExit() throws DukeException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }

    /**
     * Tests the parseCommand method for the "list" input, expecting a ListCommand instance.
     *
     * @throws DukeException If an error occurs during the test.
     */
    @Test
    public void testParseCommandList() throws DukeException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    /**
     * Tests the isExitCommand method for various exit commands,
     * expecting true, and for a non-exit command, expecting false.
     */
    @Test
    public void testIsExitCommand() {
        assertTrue(Parser.isExitCommand("bye"));
        assertTrue(Parser.isExitCommand("exit"));
        assertTrue(Parser.isExitCommand("quit"));
        assertFalse(Parser.isExitCommand("list"));
    }

    /**
     * Tests the isExitCommand method when the input is not an exit command.
     */
    @Test
    public void testIsExitCommand_NotExitCommand() {
        String input = "list";
        boolean result = Parser.isExitCommand(input);
        assertFalse(result);
    }

    /**
     * Tests the parseTag method with valid input.
     *
     * @throws DukeException if an error occurs while parsing the tag.
     */
    @Test
    public void testParseTag_ValidInput() throws DukeException {
        String input = "tag 1 important";
        String[] result = Parser.parseTag(input);

        assertEquals("1", result[0]);
        assertEquals("important", result[1]);
    }

    /**
     * Tests the parseTaskIndex method with invalid input.
     */
    @Test
    public void testParseTaskIndex_InvalidInput() {
        String input = "mark invalid";

        assertThrows(DukeException.class, () -> {
            Parser.parseTaskIndex(input);
        });
    }

    /**
     * Tests the parseTaskIndex method with valid input.
     *
     * @throws DukeException if an error occurs while parsing the task index.
     */
    @Test
    public void testParseTaskIndex_ValidInput() throws DukeException {
        String input = "mark 2";

        int result = Parser.parseTaskIndex(input);
        assertEquals(1, result);
    }
}
