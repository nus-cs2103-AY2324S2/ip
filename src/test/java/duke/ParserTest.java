package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {

    @Test
    public void testParseTodoCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("todo Task description");
        assertTrue(command != null);
    }

    @Test
    public void testParseByeCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testParseIncorrectCommand() {
        Parser parser = new Parser();
        Command command = parser.parse("randomCommand");
        assertTrue(command instanceof IncorrectCommand);
    }
}
