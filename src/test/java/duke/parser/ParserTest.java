package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.dukeexception.DukeException;

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
        LocalDateTime date = LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(date, parser.stringToDateTime("2012-12-12 12:00"));
    }
}
