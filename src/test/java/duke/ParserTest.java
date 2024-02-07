package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void testGetRequest() {
        Parser parser = new Parser();

        assertEquals(Parser.Request.BYE, parser.getRequest("bye"));
        assertEquals(Parser.Request.LIST, parser.getRequest("list"));
        assertEquals(Parser.Request.MARK, parser.getRequest("mark 1"));
        assertEquals(Parser.Request.TODO, parser.getRequest("todo Buy groceries"));
        assertEquals(Parser.Request.DEADLINE, parser.getRequest("deadline Read a book /by 2022-02-15 1800"));
        assertEquals(Parser.Request.EVENT, parser.getRequest("event Attend meeting /from 2022-02-15 1400 /to 2022-02-15 1600"));
        assertEquals(Parser.Request.DELETE, parser.getRequest("delete 2"));
        assertEquals(Parser.Request.INVALID, parser.getRequest("invalid command"));
    }
}
