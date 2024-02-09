package area;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommandSuccess(){
        Parser parser = new Parser();
        assertEquals("todo", parser.parseCommand("todo book"));
        assertEquals("deadline", parser.parseCommand("deadline assignment /by  2021-12-31"));
        assertEquals("event",parser.parseCommand("event bookreporting /from 2pm /to 6pm"));
        assertEquals("mark", parser.parseCommand("mark 1"));
    }

    @Test
    public void parseDescriptionSuccess(){
        Parser parser = new Parser();
        assertEquals("book", parser.parseDesription("todo book"));
        assertEquals("1", parser.parseDesription("unmark 1"));
    }
    
}
