package chatbot;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDate_validDate_success() {
        assertEquals(Parser.parseDate("2019-04-01 19:00"), LocalDateTime.of(2019, 4, 1, 19, 0));
    }

    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        try {
            assertEquals(Parser.parseDate("2019/04/01 19:00"), LocalDateTime.of(2019, 4, 1, 19, 0));
            fail();
        } catch (Exception e) {
            assertEquals("Text '2019/04/01 19:00' could not be parsed, unparsed text found at index 0", e.getMessage());
        }
    }
}
