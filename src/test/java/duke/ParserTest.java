package duke;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests the Parser class.
 */
public class ParserTest {

    @Test
    public void testSplitInput() {
        String input = "todo read book";
        HashMap<String, String> expected = new HashMap<>();

        expected.put("command", "todo");
        expected.put("content", "read book");
        assertEquals(expected, Parser.splitInput(input));
    }

    @Test
    public void testSplitInputWithBy() {
        String input = "deadline return book /by 2021-08-25";
        HashMap<String, String> expected = new HashMap<>();

        expected.put("command", "deadline");
        expected.put("content", "return book");
        expected.put("by", "2021-08-25");
        assertEquals(expected, Parser.splitInput(input));
    }

    @Test
    public void testSplitInputWithFromAndTo() {
        String input = "event project meeting /from 2021-08-25 /to 2021-08-26";
        HashMap<String, String> expected = new HashMap<>();

        expected.put("command", "event");
        expected.put("content", "project meeting");
        expected.put("from", "2021-08-25");
        expected.put("to", "2021-08-26");
        assertEquals(expected, Parser.splitInput(input));
    }
}
