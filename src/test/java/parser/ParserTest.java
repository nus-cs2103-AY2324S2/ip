package parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import exceptions.ArgumentException;
import exceptions.CommandException;



public class ParserTest {

    @Test
    public void parseExitCmdTest() {
        assertThrows(CommandException.class, () -> Parser.parseInput(""));
        assertDoesNotThrow(() -> assertTrue(Parser.parseInput("    bye    ") instanceof ExitCommand));
        assertThrows(CommandException.class, () -> Parser.parseInput("Bye"));
    }

    @Test
    public void parseTodoCmdTest() {
        assertThrows(ArgumentException.class, () -> Parser.parseInput(" todo     "));
        assertThrows(CommandException.class, () -> Parser.parseInput(" 123,   todo     "));
        assertDoesNotThrow(() -> assertTrue(Parser.parseInput("    todo     //f/r/123    ") instanceof AddCommand));
    }

    @Test
    public void parseDeadlineCmdTest() {
        assertThrows(ArgumentException.class, () -> Parser.parseInput(" deadline     "));
        assertThrows(ArgumentException.class, () -> Parser.parseInput(" deadline   123   "));
        assertThrows(CommandException.class, () -> Parser.parseInput(" deadline123     "));
        assertThrows(ArgumentException.class, () -> Parser.parseInput("deadline lglwlw by tmr     "));
        assertThrows(DateTimeParseException.class, () -> Parser.parseInput("deadline oqhhhq8h /by 2020 Christmas   "));
        assertDoesNotThrow(() ->assertTrue(Parser.parseInput("deadline //f/r/123  /by2020-12-25  ")
                instanceof AddCommand));
        assertDoesNotThrow(() ->assertTrue(Parser.parseInput("deadline //f/r/123  /by            2020-12-25  ")
                instanceof AddCommand));
    }

    @Test
    public void parseEventCmdTest() {
        assertThrows(ArgumentException.class, () -> Parser.parseInput("event lglwlw /from 2020-12-25  /  to    "));
        assertThrows(DateTimeParseException.class, () -> Parser.parseInput("event lglwlw /from 2020-12-25  /to    "));
        assertThrows(DateTimeParseException.class, () -> Parser.parseInput(
                "event oqhhhq8h /from 2020 Christmas   /to   2021-12-25"));
        assertDoesNotThrow(() ->assertTrue(Parser.parseInput("event //f/r/123  /from2020-12-25  /to  2021-12-25   ")
                instanceof AddCommand));
    }

    @Test
    public void parseOtherCmdsTest() {
        assertThrows(ArgumentException.class, () -> Parser.parseInput("                    mark                  "));
        assertThrows(ArgumentException.class, () -> Parser.parseInput(" unmark          TWO"));
        assertDoesNotThrow(() ->assertTrue(Parser.parseInput("delete 100")
                instanceof DeleteCommand));
        assertDoesNotThrow(() ->assertTrue(Parser.parseInput("find multiple  words")
                instanceof FindCommand));
    }

    @Test
    public void parseEventArgTest() {
        assertDoesNotThrow(() -> {
            String[] testArray = Parser.parseEventArgument("Description /from 2019-12-02 /to 2019-12-03");
            assertEquals(testArray[0], "Description ");
            assertEquals(testArray[1], " 2019-12-02 ");
            assertEquals(testArray[2], " 2019-12-03");
        });
    }
}
