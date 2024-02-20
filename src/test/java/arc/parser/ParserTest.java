package arc.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import arc.exceptions.ArcException;
import arc.exceptions.parser.InvalidArgumentException;

public class ParserTest {
    @Test
    public void parse_alphabeticalTaskNumber_invalidArgumentExceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> Parser.parse("mark x"));
    }

    @Test
    public void parse_noArguments_invalidArgumentExceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (ArcException e) {
            assertEquals("OOPS!!! Argument(s) 'task description' is missing.", e.getMessage());
        }
    }
}
