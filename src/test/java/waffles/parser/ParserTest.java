package waffles.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_deadlineCommand_success() {
        Parser p = new Parser("deadline return book /by 2024/10/10 2200");

        String expectedCommand = "DEADLINE";
        String expectedArgument = "return book /by 2024/10/10 2200";
        String expectedUnknownCommand = "";

        assertEquals(expectedCommand, p.getCommand().toString());
        assertEquals(expectedArgument, p.getArgument());
        assertEquals(expectedUnknownCommand, p.getUnknownCommand());
    }

    @Test
    public void parse_illegalCommand_exceptionThrown() {
        Parser p = new Parser("remove 1");

        String expectedCommand = "INVALID";
        String expectedArgument = "1";
        String expectedUnknownCommand = "remove";

        assertEquals(expectedCommand, p.getCommand().toString());
        assertEquals(expectedArgument, p.getArgument());
        assertEquals(expectedUnknownCommand, p.getUnknownCommand());
    }

}
