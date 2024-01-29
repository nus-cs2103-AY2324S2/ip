package ezra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParser {
    @Test
    public void TestParseDeadline() throws WrongFormatException {
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDeadline("deadline return books"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDeadline("deadline /by 29/01/24 1800"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDeadline("deadline return books /by"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDeadline("deadline return books by 29/01/24 1800"));
        assertEquals(new Deadline("return books", "29/01/2024 1800"),
                Parser.parseDeadline("deadline return books /by 29/01/2024 1800"));
    }

    @Test
    public void TestParseDelete() throws WrongFormatException {
        assertEquals(0, Parser.parseDelete("delete 1"));
        assertEquals(9, Parser.parseDelete("delete 10"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDelete("delete -1"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDelete("delete abc"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDelete("delete 12a"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDelete("delete"));
        assertThrows(WrongFormatException.class, () ->
                Parser.parseDelete("delete "));
    }
}
