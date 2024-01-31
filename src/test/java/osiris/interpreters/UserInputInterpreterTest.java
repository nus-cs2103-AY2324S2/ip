package osiris.interpreters;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import osiris.commands.Command;
import osiris.commands.NoCommand;
import osiris.commands.UnsupportedCommand;
import osiris.commands.addDeadlineTaskCommand;
import osiris.commands.addEventTaskCommand;
import osiris.commands.addToDoTaskCommand;
import osiris.commands.markTaskCompletedCommand;
import osiris.commands.markTaskIncompleteCommand;
import osiris.commands.printUserTasksCommand;
import osiris.commands.removeTaskCommand;
import osiris.commands.terminateChatCommand;

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
        assertInstanceOf(terminateChatCommand.class, command);
    }

    /**
     * Test for interpreting the "list" command.
     */
    @Test
    public void printUserTasksCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("list");
        assertInstanceOf(printUserTasksCommand.class, command);
    }

    /**
     * Test for interpreting the "mark" command.
     */
    @Test
    public void markTaskCompletedCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("mark 1");
        assertInstanceOf(markTaskCompletedCommand.class, command);
    }

    /**
     * Test for interpreting the "unmark" command.
     */
    @Test
    public void markTaskIncompleteCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("unmark 1");
        assertInstanceOf(markTaskIncompleteCommand.class, command);
    }

    /**
     * Test for interpreting the "delete" command.
     */
    @Test
    public void removeTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("delete 1");
        assertInstanceOf(removeTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "todo" command.
     */
    @Test
    public void addToDoTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("todo Test");
        assertInstanceOf(addToDoTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "deadline" command.
     */
    @Test
    public void addDeadlineTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("deadline Test /by 01-01-2024");
        assertInstanceOf(addDeadlineTaskCommand.class, command);
    }

    /**
     * Test for interpreting the "event" command.
     */
    @Test
    public void addEventTaskCommandTest() {
        Command command = UserInputInterpreter.getInstance()
                .interpretUserInput("event Test /from 01-01-2024 0000 /to 01-01-2024 2359");
        assertInstanceOf(addEventTaskCommand.class, command);
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
