package junjie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

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

    @Test
    public void testSplitInputWithTag() {
        String input = "todo read book /tag important";
        HashMap<String, String> expected = new HashMap<>();

        expected.put("command", "todo");
        expected.put("content", "read book");
        expected.put("tag", "important");
        assertEquals(expected, Parser.splitInput(input));
    }

    @Test
    public void testSplitInputWithMultipleTags() {
        String input = "todo read book /tag important urgent";
        HashMap<String, String> expected = new HashMap<>();

        expected.put("command", "todo");
        expected.put("content", "read book");
        expected.put("tag", "important urgent");
        assertEquals(expected, Parser.splitInput(input));
    }
}
