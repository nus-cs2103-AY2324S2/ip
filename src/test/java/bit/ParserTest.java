package bit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void testParse() {
        Parser parser = new Parser();
        assertEquals("bye", parser.parse("bye"));
        assertEquals("list", parser.parse("list"));
        assertEquals("add", parser.parse("mark"));
        assertEquals("add", parser.parse("marky"));
        assertEquals("mark", parser.parse("mark 2"));
        assertEquals("add", parser.parse("unmark"));
        assertEquals("add", parser.parse("unmarko"));
        assertEquals("unmark", parser.parse("unmark 1"));
        assertEquals("delete", parser.parse("delete 1"));
        assertEquals("add", parser.parse("delete"));
    }

}
