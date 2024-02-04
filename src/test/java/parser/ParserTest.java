package parser;
import exceptionhandling.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void parseMarkAction() {
        DukeException error = assertThrows(DukeException.class, () ->
                Parser.parseCommand("mark"), "Expected parse to throw but it didn't.");
        assertTrue(error.getMessage().contains("Please include a task index to mark"));
    }

    @Test
    public void parseEventCreation() {
        DukeException error = assertThrows(DukeException.class, () ->
                Parser.parseCommand("event"), "Expected parse to throw but it didn't.");
        assertTrue(error.getMessage().contains("Please write a description and the time period for your task!"));
    }

    @Test
    public void parseEventCreation2() {
        DukeException error = assertThrows(DukeException.class, () ->
                Parser.parseCommand("event birthday"), "Expected parse to throw but it didn't.");
        assertTrue(error.getMessage().contains("Please include a time period by using from and to keyword such as" +
                "'/from today /to tomorrow"));
    }
}
