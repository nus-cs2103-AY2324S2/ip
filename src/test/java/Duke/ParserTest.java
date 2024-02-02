package Duke;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseCommandExit() throws DukeException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseCommandList() throws DukeException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testIsExitCommand() {
        assertTrue(Parser.isExitCommand("bye"));
        assertTrue(Parser.isExitCommand("exit"));
        assertTrue(Parser.isExitCommand("quit"));
        assertFalse(Parser.isExitCommand("list"));
    }
}


