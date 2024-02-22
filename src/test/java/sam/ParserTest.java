package sam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testTodoWithoutDescription() {
        Exception exception = assertThrows(SamException.class,
                () -> Parser.parse("todo"));
        assertEquals("Please provide a task description.", exception.getMessage());
    }

    @Test
    public void testEventWithoutDescription() {
        Exception exception = assertThrows(SamException.class,
                () -> Parser.parse("event /from 29/1/2024 1800"));
        assertEquals("Invalid format for event, please provide event details by using /from and /to.",
                exception.getMessage());
    }

    @Test
    public void testMarkWithoutIndex() {
        Exception exception = assertThrows(SamException.class,
                () -> Parser.parse("mark"));
        assertEquals("Please provide a task number.",
                exception.getMessage());
    }
}