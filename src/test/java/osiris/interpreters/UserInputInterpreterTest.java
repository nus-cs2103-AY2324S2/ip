package osiris.interpreters;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import osiris.commands.Command;
import osiris.commands.NoCommand;
import osiris.commands.UnsupportedCommand;
import osiris.commands.AddDeadlineTaskCommand;
import osiris.commands.AddEventTaskCommand;
import osiris.commands.AddToDoTaskCommand;
import osiris.commands.MarkTaskCompleteCommand;
import osiris.commands.MarkTaskIncompleteCommand;
import osiris.commands.PrintUserTasksCommand;
import osiris.commands.RemoveTaskCommand;
import osiris.commands.TerminateChatCommand;

/**
 * Unit tests for the UserInputInterpreter class.
 */
public class UserInputInterpreterTest {

    /**
     * Test for interpreting the "bye" command.
     */
    @Test
    public void terminateChatCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("bye");
        assertInstanceOf(TerminateChatCommand.class, command);
    }

    /**
     * Test for interpreting the "list" command.
     */
    @Test
    public void printUserTasksCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("list");
        assertInstanceOf(PrintUserTasksCommand.class, command);
    }

    /**
     * Test for interpreting the "mark" command.
     */
    @Test
    public void markTaskCompletedCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("mark 1");
        assertInstanceOf(MarkTaskCompleteCommand.class, command);
    }

    /**
     * Test for interpreting the "unmark" command.
     */
    @Test
    public void markTaskIncompleteCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("unmark 1");
        assertInstanceOf(MarkTaskIncompleteCommand.class, command);
    }

    /**
     * Test for interpreting the "delete" command.
     */
    @Test
    public void removeTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("delete 1");
        assertInstanceOf(RemoveTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "todo" command.
     */
    @Test
    public void addToDoTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("todo Test");
        assertInstanceOf(AddToDoTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "deadline" command.
     */
    @Test
    public void addDeadlineTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("deadline Test /by 01-01-2024");
        assertInstanceOf(AddDeadlineTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "event" command.
     */
    @Test
    public void addEventTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance()
                .interpretUserInput("event Test /from 01-01-2024 0000 /to 01-01-2024 2359");
        assertInstanceOf(AddEventTaskCommand.class, command);
    }

    /**
     * Test for interpreting unsupported commands.
     */
    @Test
    public void unsupportedCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("Invalid.");
        assertInstanceOf(UnsupportedCommand.class, command);
    }

    /**
     * Test for interpreting commands with no specific action.
     */
    @Test
    public void noCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("todo");
        assertInstanceOf(NoCommand.class, command);
        command = UserInputInterpreter.getInstance().interpretUserInput("deadline");
        assertInstanceOf(NoCommand.class, command);
        command = UserInputInterpreter.getInstance().interpretUserInput("event");
        assertInstanceOf(NoCommand.class, command);
    }
}
