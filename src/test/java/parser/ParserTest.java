package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.AddDeadlineCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.InvalidCommand;
import commands.ListCommand;

public class ParserTest {

    private final Parser parser = new Parser();
    @Test
    public void testParseList() {
        String userInput = "list";
        Command c = parser.parse(userInput);
        assertEquals(c, new ListCommand());
    }

    @Test
    public void testParseTodo() {
        String userInput = "todo test";
        Command c = parser.parse(userInput);
        assertEquals(c, new AddTodoCommand());
    }

    @Test
    public void testParseDeadlineValidDate() {
        String userInput = "Deadline test by 23/2/2023 0000";
        Command c = parser.parse(userInput);
        assertEquals(c, new AddDeadlineCommand());
    }

    @Test
    public void testParseDeadlineInvalidtDate() {
        String userInput = "Deadline test by 23/233/2023 0000";
        Command c = parser.parse(userInput);
        assertEquals(c, new InvalidCommand(""));
    }
}
