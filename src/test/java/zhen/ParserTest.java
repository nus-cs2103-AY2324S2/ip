package zhen;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    public void testWellFormedEvent() {
        String input = "return book /from 2023-11-11 /to 2023-02-03";
        String[] expected = {"return book", "2023-11-11", "2023-02-03"};
        assertArrayEquals(expected, Parser.processEventMsg(input));
    }

    @Test
    public void testEmptyEvent() {
        String input = "";
        String[] expected = {"", "", ""};
        assertArrayEquals(expected,  Parser.processEventMsg(input));
    }

    @Test
    public void testPartialEmptyEvent() {
        String input = "return book";
        String[] expected = {"return book", "", ""};
        assertArrayEquals(expected,  Parser.processEventMsg(input));
    }

    @Test
    public void testLackFromEvent() {
        String input = "return book /to someday";
        String[] expected = {"return book", "", "someday"};
        assertArrayEquals(expected,  Parser.processEventMsg(input));
    }

    @Test
    public void testLackToEvent() {
        String input = "return book /from someday";
        String[] expected = {"return book", "someday", ""};
        assertArrayEquals(expected,  Parser.processEventMsg(input));
    }

    @Test
    public void testWellFormedDeadline() {
        String input = "return book /by 2023-11-11";
        String[] expected = {"return book", "2023-11-11"};
        assertArrayEquals(expected, Parser.processDeadlineMsg(input));
    }
    @Test
    public void testEmptyDeadline() {
        String input = "";
        String[] expected = {"", ""};
        assertArrayEquals(expected,  Parser.processDeadlineMsg(input));
    }

    @Test
    public void testLackByDeadline() {
        String input = "return something";
        String[] expected = {"return something", ""};
        assertArrayEquals(expected,  Parser.processDeadlineMsg(input));
    }
    @Test
    public void testOnlyByDeadline() {
        String input = "/by monday";
        String[] expected = {"", "monday"};
        assertArrayEquals(expected,  Parser.processDeadlineMsg(input));
    }

}