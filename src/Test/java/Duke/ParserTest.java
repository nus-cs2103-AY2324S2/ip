package Duke;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseInvalidCommand() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String result = Parser.parse("invalid command", tasks, ui, storage);

        assertEquals("Gurl I'm sorry, idk what that means :-(", result);
    }

    @Test
    public void testParseTodoCommand() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String result = Parser.parse("todo Test Todo", tasks, ui, storage);

        assertEquals("I'm sorry, but I don't understand that command.", result);
    }
}
