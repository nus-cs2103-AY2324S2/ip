package panda.component;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import panda.command.AlterMarkCommand;
import panda.command.DeleteCommand;
import panda.command.ExitCommand;
import panda.command.FindCommand;
import panda.command.NewTaskCommand;
import panda.command.PrintListCommand;
import panda.exception.PandaException;
import panda.task.Deadline;
import panda.task.Event;
import panda.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseCommand_validCommands_success() throws PandaException {
        // valid command results in exit command
        assertEquals(new ExitCommand(), Parser.parse("bye"));

        // valid command results in print command
        assertEquals(new PrintListCommand(), Parser.parse("list"));

        // valid command results in alter mark command
        assertEquals(new AlterMarkCommand(1, true), Parser.parse("mark 1"));

        // valid command results in alter mark command (marked == false)
        assertEquals(new AlterMarkCommand(1, false), Parser.parse("unmark 1"));

        // valid command results in delete command
        assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));

        // valid add task command results in new task command (todo)
        assertEquals(new NewTaskCommand(new Todo("read book")), Parser.parse("todo read book"));

        // valid add task command results in new task command (deadline)
        assertEquals(new NewTaskCommand(new Deadline("read book", "Oct 15 2019 12AM")), Parser.parse("deadline read book /by Oct 15 2019 12AM"));

        // valid add task command results in new task command (event)
        assertEquals(new NewTaskCommand(new Event("read book", "Oct 15 2019 12AM", "Oct 16 2019 12AM")), Parser.parse("event read book /from Oct 15 2019 12AM /to Oct 16 2019 12AM"));

        // valid command results in find command
        assertEquals(new FindCommand("read"), Parser.parse("find read"));

    }

    @Test
    public void parseCommand_invalidCommands_exceptionThrown() throws PandaException {
        try {
            assertEquals(0, Parser.parse("clear"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS! Panda doesn't have that command.", e.getMessage());
        }

        try {
            assertEquals(0, Parser.parse("mark read book"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS! Please use the correct format for this command.", e.getMessage());
        }

    }
}
