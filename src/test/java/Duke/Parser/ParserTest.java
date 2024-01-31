package Duke.Parser;

import Duke.DukeException.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParse() throws DukeException {
        Parser parser = new Parser();
        String input = "todo Read book";
        assertEquals("todo", parser.parse(input)[0]);
    }

    @Test
    public void testStringToDate() throws DukeException {
        Parser parser = new Parser();
        LocalDate date = LocalDate.parse("2019-12-12");
        assertEquals(date, parser.stringToDate("2019-12-12"));
    }
}
