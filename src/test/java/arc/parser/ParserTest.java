package arc.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import arc.exceptions.parser.InvalidArgumentException;

public class ParserTest {
    @Test
    public void parse_alphabeticalTaskNumber_invalidArgumentExceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parse("mark x"));
    }
}
