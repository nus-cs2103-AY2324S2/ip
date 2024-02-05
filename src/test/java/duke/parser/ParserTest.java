package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
        LocalDate date = LocalDate.parse("2019-12-12");
        assertEquals(date, parser.stringToDate("2019-12-12"));
    }
}
