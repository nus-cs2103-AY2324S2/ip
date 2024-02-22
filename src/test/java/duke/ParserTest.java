package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTest() {
        Parser p = new Parser();
        String test = p.interpret("reset");
        assertEquals(test, "List cleared!");
        String test2 = p.interpret("bye");
        assertEquals(test2, "Bye. Hope to see you again soon!");
        String test3 = p.interpret("randomnonsense");
        assertEquals(test3, "Sorry, no idea what u talking about lulz");
    }
}
