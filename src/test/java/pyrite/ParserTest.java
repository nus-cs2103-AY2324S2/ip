package pyrite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pyrite.command.AddCommand;
import pyrite.command.Command;
import pyrite.command.DeleteCommand;
import pyrite.command.ExitCommand;
import pyrite.command.ListCommand;
import pyrite.command.StatusCommand;

/**
 * Test class for Parser.
 */
public class ParserTest {
    // Test cases suggested by Github Copilot
    @Test
    public void parse_bye_success() {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }
    @Test
    public void parse_list_success() {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }
    @Test
    public void parse_mark_success() {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof StatusCommand);
    }
    @Test
    public void parse_unmark_success() {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof StatusCommand);
    }
    @Test
    public void parse_delete_success() {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }
    @Test
    public void parse_todo_success() {
        Command command = Parser.parse("todo test");
        assertTrue(command instanceof AddCommand);
    }
    @Test
    public void parse_deadline_success() {
        Command command = Parser.parse("deadline test /by 2024-01-01T00:00");
        assertTrue(command instanceof AddCommand);
    }
    @Test
    public void parse_event_success() {
        Command command = Parser.parse("event test /from 2024-01-01T00:00 /to 2024-01-01T00:00");
        assertTrue(command instanceof AddCommand);
    }
    @Test
    public void parse_unknownCommand_success() {
        try {
            Command command = Parser.parse("not a valid command");
        } catch (Exception e) {
            assertTrue(e instanceof PyriteException);
        }
    }
    @Test
    public void parse_emptyString_success() {
        try {
            Command command = Parser.parse("");
        } catch (Exception e) {
            assertTrue(e instanceof PyriteException);
        }
    }
}
