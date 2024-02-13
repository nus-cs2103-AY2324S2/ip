package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for Parser class.
 */
public class ParserTest {

    /**
     * Tests the parse method of Parser.
     */
    @Test
    public void testParseInvalidCommand() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String result = Parser.parse("invalid command", tasks, ui, storage);

        assertEquals("Gurl I'm sorry, idk what that means :-(", result);
    }

    /**
     * Tests the parse method of Parser.
     */
    @Test
    public void testParseTodoCommand() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String result = Parser.parse("todo Test Todo", tasks, ui, storage);

        assertEquals("I'm sorry, but I don't understand that command.", result);
    }
}
