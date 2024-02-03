package anita;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_success() throws Exception {
        assertEquals("todo", new Parser().parseCommand("todo ligma"));

        assertEquals("event", new Parser().parseCommand("event project meeting /from Mon 2pm /to 4pm"));

        assertEquals("deadline", new Parser().parseCommand("deadline return book /by Sunday"));

        assertEquals("delete", new Parser().parseCommand("delete 69"));
    }

    @Test
    public void indexParser_success() throws Exception {
        assertEquals(2, new Parser().indexParser("delete 2"));

        assertEquals(3, new Parser().indexParser("delete 3"));
    }
}
