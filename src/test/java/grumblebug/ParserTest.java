package grumblebug;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void correctFormatting() {
        String format = "dd MMM yyyy";
        Parser p = new Parser(format);
        LocalDate ld = p.parse("26 Aug 2023");
        String actual = ld.toString();
        String expected = "2023-08-26";
        assertEquals(actual, expected);
    }
}
