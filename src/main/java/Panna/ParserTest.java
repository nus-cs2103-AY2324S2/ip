package Panna;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void checkParse() {
        Parser p = new Parser("dd MMM yyyy");
        LocalDate l = p.parse("22 Jul 2004");
        String expected = "2004-07-22";
        assertEquals(expected, l.toString());
    }


}
