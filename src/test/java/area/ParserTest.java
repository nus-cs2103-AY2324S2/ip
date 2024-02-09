package area;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_addTasks_success(){
        Parser parser = new Parser();
        assertEquals("todo", parser.parseCommand("todo book"));
        assertEquals("deadline", parser.parseCommand("deadline assignment /by  2021-12-31"));
        assertEquals("event",parser.parseCommand("event bookreporting /from 2pm /to 6pm"));
        assertEquals("mark", parser.parseCommand("mark 1"));
    }

    @Test
    public void parseDescription_addDescriptions_success(){
        Parser parser = new Parser();
        assertEquals("book", parser.parseDesription("todo book"));
        assertEquals("2", parser.parseDesription("unmark 2"));
    }
    
}
