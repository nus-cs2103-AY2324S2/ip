package tony;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class TestParser {

    @Test
    public void testParseList() {
        Parser parser = new Parser();
        String input = "list";
        String command = parser.parseCommand(input);
        assertEquals("list", command);
    }

    @Test
    public void testParseTodo() {
        Parser parser = new Parser();
        String input = "todo borrow book";
        String description = parser.parseDescription(input);
        assertEquals("borrow book", description);
    }

    @Test
    public void testParseDeadline() {
        Parser parser = new Parser();
        String input = "deadline Complete project /2022-12-31T14:30";
        String[] result = parser.parseTasksWithDate(input);
        assertArrayEquals(new String[]{"Complete project ", "2022-12-31T14:30"}, result);
    }

    @Test
    public void testParseEvent() {
        Parser parser = new Parser();
        String input = "event project meeting /from 2019-10-15T21:30 /to 2019-10-17T22:30";
        String[] result = parser.parseTasksWithDate(input);
        assertArrayEquals(new String[]{"project meeting ", "from 2019-10-15T21:30 ", "to 2019-10-17T22:30"}, result);
    }

    @Test
    public void testParseDate() {
        try {
            Parser parser = new Parser();
            String date = "2022-12-31T14:30";
            LocalDateTime parsedDateTime = parser.parseDate(date);
            LocalDateTime expectedDateTime = LocalDateTime.of(2022, 12, 31, 14, 30);
            assertEquals(expectedDateTime, parsedDateTime);
        } catch (Exception e) {
            assertEquals(0, 1);
        }
    }
}
