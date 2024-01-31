package henry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testTodoWithoutDescription() {
        Exception exception = assertThrows(HenryException.class,
                () -> Parser.parse("todo"));
        assertEquals("No description provided", exception.getMessage());
    }

    @Test
    public void testEventWithoutDescription() {
        Exception exception = assertThrows(HenryException.class,
                () -> Parser.parse("event /from 29/1/2024 1800"));
        assertEquals("Missing /from or /to",
                exception.getMessage());
    }

    @Test
    public void testEventWithoutToDateTime() {
        Exception exception = assertThrows(HenryException.class,
                () -> Parser.parse("event event /from 29/2/2012"));
        assertEquals("Missing /from or /to",
                exception.getMessage());
    }
}