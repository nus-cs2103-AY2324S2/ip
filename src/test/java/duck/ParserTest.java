package duck;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
}
