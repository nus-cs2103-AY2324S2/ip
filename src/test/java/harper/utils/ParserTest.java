package harper.utils;

import harper.commands.AddCommand;
import harper.exceptions.HarperInvalidDateTimeException;
import harper.exceptions.HarperInvalidDeadlineException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void handleDeadline_correctFormat_success() {
        String command = "deadline read book /by 12/12/2001 15:20";
        assertDoesNotThrow(() -> Parser.handleDeadline(command));
        assertTrue(Parser.handleDeadline(command) instanceof AddCommand);
    }

    @Test
    public void handleDeadline_incorrectFormat_exceptionThrown() {
        String command1 = "deadline /by 12/12/1212 12:12";
        String command2 = "deadline read book /by 12/12/1212";
        String command3 = "deadline read book";

        assertThrows(HarperInvalidDeadlineException.class,
                () -> Parser.handleDeadline(command1),
                "Should throw HarperInvalidDeadlineException");

        assertThrows(HarperInvalidDateTimeException.class,
                () -> Parser.handleDeadline(command2),
                "Should throw HarperInvalidDateTimeException");

        assertThrows(HarperInvalidDeadlineException.class,
                () -> Parser.handleDeadline(command3),
                "Should throw HarperInvalidDeadlineException");
    }
}

