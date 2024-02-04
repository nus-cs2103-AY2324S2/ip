import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Parser;

public class ParserTest {

    @Test
    void parseDtString_validInput_returnsNewDateTime() throws Exception {
        String s = "10/10/10 1100";
        Assertions.assertEquals(LocalDateTime.parse("10/10/10, 11:00 AM",
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)), Parser.parseDtString(s));
    }

    @Test
    void parseDtString_invalidInput_throwsDateTimeParseException() {
        String s = "10/10/10 10";
        Assertions.assertThrows(DateTimeParseException.class, () -> Parser.parseDtString(s));
    }
}
