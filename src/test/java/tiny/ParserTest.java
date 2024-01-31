package tiny;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tiny.exceptions.TinyException;



public class ParserTest {

    @Test
    public void unknownCommandTest() throws TinyException {
        Parser parser = new Parser();
        String actualSring = parser.parse("Random Unknown Command", new TaskList());
        assertEquals("I'm sorry, but I don't know what that means :-(", actualSring);
    }

    @Test
    public void byeTest() throws TinyException {
        Parser parser = new Parser();
        String actualSring = parser.parse("bye", new TaskList());
        assertEquals(true, parser.isExit());
        assertEquals("Bye. Hope to see you again soon!", actualSring);
    }
}
