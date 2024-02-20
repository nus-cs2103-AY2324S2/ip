package panna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void checkParse() {
        Parser p = new Parser("dd MMM yyyy");
        LocalDate l = p.parse("22 Jul 2004");
        String expected = "2004-07-22";
        assertEquals(expected, l.toString());
    }


}
