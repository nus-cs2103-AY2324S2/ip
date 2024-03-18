package gulie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GulieParserTest {
    @Test
    public void dateParseTest() {
        GulieParser parser = new GulieParser();
        GulieException ge = assertThrows(GulieException.class,
                () -> parser.parse("deadline d /by 12.05.24"));
        assertEquals(ge.getMessage(), "The datetime that you have given is invalid.");
        ge = assertThrows(GulieException.class,
                () -> parser.parse("deadline d /by 25.12.12T03:24"));
        assertEquals(ge.getMessage(), "The datetime that you have given is invalid.");
        ge = assertThrows(GulieException.class,
                () -> parser.parse("deadline d /by 25-13-24T03:24"));
        assertEquals(ge.getMessage(), "The datetime that you have given is invalid.");
        assertDoesNotThrow(() -> parser.parse("deadline d /by 2024-03-24T03:24"));
    }
}
