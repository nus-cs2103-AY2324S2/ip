package luna;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void dateInvalidityTest() {
        assertFalse(Parser.isDateInvalid("23-12-2023"));
        assertFalse(Parser.isDateInvalid("11-12-2023"));
        assertFalse(Parser.isDateInvalid("23-12-1023"));
        assertTrue(Parser.isDateInvalid("23221023"));
    }

    @Test
    public void nonNumericTest() {
        assertFalse(Parser.isNonInteger("2"));
        assertFalse(Parser.isNonInteger("10000"));
        assertTrue(Parser.isNonInteger("10000.0"));
    }
}
