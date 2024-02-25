package sam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testTodoWithoutDescription() {
        Exception exception = assertThrows(SamException.class, () -> Parser.parse("todo"));
        assertEquals("Please provide a task description.", exception.getMessage());
    }

    @Test
    public void testEventWithoutDescription() {
        Exception exception = assertThrows(SamException.class, ()
                -> Parser.parse("event /from 29/1/2024 1800 /to 29/1/2024 1900"));
        assertEquals("Please check whether you have provided a description and both start/end time.",
                exception.getMessage());
    }

    @Test
    public void testEventWithoutToDateTime() {
        Exception exception = assertThrows(SamException.class, ()
                -> Parser.parse("event event /from 29/2/2012"));
        assertEquals("Invalid format for event, please provide event details by using /from and /to.",
                exception.getMessage());
    }
}
