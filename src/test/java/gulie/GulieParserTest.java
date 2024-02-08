package gulie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GulieParserTest {
    @Test
    public void invalidDateTest() {
        GulieParser parser = new GulieParser();
        GulieException ge = Assertions.assertThrows(GulieException.class,
                () -> parser.parse("deadline /by 12.05.24"));
        assertEquals(ge.getMessage(), "The datetime that you have given is invalid.");
    }
}
