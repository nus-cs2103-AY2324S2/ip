package bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void testParse() {
        String[] expected = { "todo", "Test ToDo" };
        assertArrayEquals(expected, parser.parse("todo Test ToDo"));
    }

    @Test
    public void testParseCommand() {
        assertEquals("todo", parser.parseCommand("todo Test ToDo"));
    }

    @Test
    public void testParseIndex() {
        assertEquals(1, parser.parseIndex("delete 1"));
    }

    @Test
    public void testParseDescription() {
        assertEquals("Test ToDo", parser.parseDescription("todo Test ToDo"));
    }

    @Test
    public void testParseDeadline() {
        String[] expected = { "Test Deadline", "2022-12-31" };
        assertArrayEquals(expected, parser.parseDeadline("deadline Test Deadline /by 2022-12-31"));
    }

    @Test
    public void testParseEvent() {
        String[] expected = { "Test Event", "2022-12-31", "2023-01-01" };
        assertArrayEquals(expected, parser.parseEvent("event Test Event /from 2022-12-31 /to 2023-01-01"));
    }
}