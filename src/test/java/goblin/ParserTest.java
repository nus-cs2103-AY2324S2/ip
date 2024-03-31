package goblin;

import goblin.command.*;
import goblin.exception.OrkException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void parse_todo() throws OrkException {
        assertEquals(new AddTodoCommand("have fun"), Parser.parse("todo have fun"));
    }

    @Test
    public void parse_deadline() throws OrkException {
        assertEquals(new AddDeadlineCommand("return book /by 2/12/2019 1800"), Parser.parse("deadline return book /by 2/12/2019 1800"));
    }
}
