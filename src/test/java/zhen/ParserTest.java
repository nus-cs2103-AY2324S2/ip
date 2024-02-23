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
    public void testExtraSpacesEvent() {
        String input = "return book  /from  2023-11-11  /to  2023-02-03";
        String[] expected = {"return book", "2023-11-11", "2023-02-03"};
        assertArrayEquals(expected, Parser.processEventMsg(input));
    }

    @Test
    public void testExtraFromEvent() {
        String input = "return book  /from  2023-11-11  /to  2023-02-03 /from 2034-11-11";
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

    @Test
    public void testExtraByDeadline() {
        String input = "/by monday/by sunday";
        String[] expected = {"", "monday"};
        assertArrayEquals(expected,  Parser.processDeadlineMsg(input));
    }

    @Test
    public void testOnlyMisplacedByDeadline() {
        String input = "/bymonday";
        String[] expected = {"", "monday"};
        assertArrayEquals(expected,  Parser.processDeadlineMsg(input));
    }

    @Test
    public void testValidTag() {
        String input = "tag 1 important";
        String[] expected = {"1", "important"};
        assertArrayEquals(expected,  Parser.processTagMsg(input));
    }

    @Test
    public void testMoreTagInputs() {
        String input = "tag 1 important urgent";
        String[] expected = {"1", "important"};
        assertArrayEquals(expected,  Parser.processTagMsg(input));
    }

    @Test
    public void testMoreDigitIndex() {
        String input = "tag 12345 important";
        String[] expected = {"12345", "important"};
        assertArrayEquals(expected,  Parser.processTagMsg(input));
    }

    @Test
    public void testLessTagInputs() {
        String input = "tag 1";
        assertThrows(RuntimeException.class, ()->Parser.processTagMsg(input));
    }

    @Test
    public void testNoTagInputs() {
        String input = "tag ";
        assertThrows(RuntimeException.class, ()->Parser.processTagMsg(input));
    }

    @Test
    public void invalidTagKeyword() {
        String input = "tagg 1 important";
        assertThrows(RuntimeException.class, ()->Parser.processTagMsg(input));
    }

    @Test
    public void emptyInput() {
        String input = "";
        assertThrows(RuntimeException.class, ()->Parser.processTagMsg(input));
    }
}