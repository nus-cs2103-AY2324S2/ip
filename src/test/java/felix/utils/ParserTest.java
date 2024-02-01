package felix.utils;

import felix.command.Command;
import felix.exception.FelixException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testInvalidCommand(){
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("hello world");
        } catch (FelixException err) {
            assertEquals("Unrecognised command", err.getMessage());
        }
    }

    @Test
    public void testInvalidNumberAfterMark() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("mark test");
        } catch (FelixException err) {
            assertEquals("Enter a number after \"mark\"", err.getMessage());
        }
    }

    @Test
    public void testInvalidNumberAfterUnmark() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("unmark test");
        } catch (FelixException err) {
            assertEquals("Enter a number after \"unmark\"", err.getMessage());
        }
    }

    @Test
    public void testInvalidNumberAfterDelete() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("delete test");
        } catch (FelixException err) {
            assertEquals("Enter a number after \"delete\"", err.getMessage());
        }
    }

    @Test
    public void testEmptyTodoDescription() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("todo");
        } catch (FelixException err) {
            assertEquals("The description of a todo cannot be empty", err.getMessage());
        }
    }

    @Test
    public void testInvalidDeadlineFormat() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("deadline test Monday 6pm");
        } catch (FelixException err) {
            assertEquals("Command does not follow this format: deadline {description} by {end_datetime}", err.getMessage());
        }
    }

    @Test
    public void testInvalidDeadlineEndDatetime() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("deadline test /by 2023-11-15");
        } catch (FelixException err) {
            assertEquals("datetime for deadline is not in the format \"yyyy-MM-dd HHmm\"", err.getMessage());
        }
    }

    @Test
    public void testInvalidEventFormat() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("event test from Monday to Friday");
        } catch (FelixException err) {
            assertEquals("Command does not follow this format: event {description} /from {start_datetime} /to {end_datetime}", err.getMessage());
        }
    }

    @Test
    public void testInvalidEventDatetime() {
        Parser parser = new Parser();
        try {
            Command res = parser.getCommand("event test /from Monday /to Friday");
        } catch (FelixException err) {
            assertEquals("datetime not in the format \"yyyy-MM-dd HHmm\"", err.getMessage());
        }
    }
} 
