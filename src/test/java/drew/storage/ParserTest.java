package drew.storage;

import org.junit.jupiter.api.Test;

import drew.command.ByeCommand;
import drew.command.Command;
import drew.command.DeadlineCommand;
import drew.command.DeleteCommand;
import drew.command.EventCommand;
import drew.command.FindCommand;
import drew.command.ListCommand;
import drew.command.MarkCommand;
import drew.command.TodoCommand;
import drew.command.UnknownCommand;
import drew.command.UnmarkCommand;
import drew.ui.Parser;

public class ParserTest {
    @Test
    public void getCommand_eventCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("event concert /from 2024-01-01 /to 2024-01-02");
        assert command instanceof EventCommand;
    }

    @Test
    public void getCommand_deadlineCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("deadline homework /by 2024-01-02");
        assert command instanceof DeadlineCommand;
    }

    @Test
    public void getCommand_todoCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("todo pack luggage");
        assert command instanceof TodoCommand;
    }

    @Test
    public void getCommand_findCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("find something");
        assert command instanceof FindCommand;
    }

    @Test
    public void getCommand_markCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("mark 1");
        assert command instanceof MarkCommand;
    }

    @Test
    public void getCommand_unmarkCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("unmark 1");
        assert command instanceof UnmarkCommand;
    }

    @Test
    public void getCommand_listCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("list");
        assert command instanceof ListCommand;
    }

    @Test
    public void getCommand_deleteCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("delete 1");
        assert command instanceof DeleteCommand;
    }

    @Test
    public void getCommand_unknownCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("blah blah");
        assert command instanceof UnknownCommand;
    }

    @Test
    public void getCommand_byeCommand_assertionSucceed() {
        Parser parser = new Parser();
        Command command = parser.getCommand("bye");
        assert command instanceof ByeCommand;
    }
}
