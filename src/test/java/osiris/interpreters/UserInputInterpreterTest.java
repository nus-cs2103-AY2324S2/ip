package osiris.interpreters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import osiris.commands.AddDeadlineTaskCommand;
import osiris.commands.AddEventTaskCommand;
import osiris.commands.AddToDoTaskCommand;
import osiris.commands.Command;
import osiris.commands.DeleteTaskCommand;
import osiris.commands.MarkTaskCompleteCommand;
import osiris.commands.MarkTaskIncompleteCommand;
import osiris.commands.PrintUserTasksCommand;
import osiris.commands.TerminateChatCommand;
import osiris.commands.UnsupportedCommand;
import osiris.exceptions.OsirisException;

/**
 * Unit tests for the UserInputInterpreter class.
 */
public class UserInputInterpreterTest {

    /**
     * Tests interpreting the "bye" command.
     */
    @Test
    public void interpretUserInput_bye_terminateChatCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("bye");
        assertInstanceOf(TerminateChatCommand.class, command);
    }

    /**
     * Tests interpreting the "list" command.
     */
    @Test
    public void interpretUserInput_list_printUserTasksCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("list");
        assertInstanceOf(PrintUserTasksCommand.class, command);
    }

    /**
     * Tests interpreting the "mark" command.
     */
    @Test
    public void interpretUserInput_mark_markTaskCompleteCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("mark 1");
        assertInstanceOf(MarkTaskCompleteCommand.class, command);
    }

    /**
     * Tests interpreting the "unmark" command.
     */
    @Test
    public void interpretUserInput_unmark_markTaskIncompleteCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("unmark 1");
        assertInstanceOf(MarkTaskIncompleteCommand.class, command);
    }

    /**
     * Tests interpreting the "delete" command.
     */
    @Test
    public void interpretUserInput_delete_removeTaskCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("delete 1");
        assertInstanceOf(DeleteTaskCommand.class, command);
    }

    /**
     * Tests interpreting the "todo" command.
     */
    @Test
    public void interpretUserInput_todo_addToDoTaskCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("todo Test");
        assertInstanceOf(AddToDoTaskCommand.class, command);
    }

    /**
     * Tests interpreting the "deadline" command.
     */
    @Test
    public void interpretUserInput_deadline_addDeadlineTaskCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("deadline Test /by 01-01-2024");
        assertInstanceOf(AddDeadlineTaskCommand.class, command);
    }

    /**
     * Tests interpreting the "event" command.
     */
    @Test
    public void interpretUserInput_event_addEventTaskCommandReturned() {
        Command command = UserInputInterpreter.getInstance()
                .interpretUserInput("event Test /from 01-01-2024 0000 /to 01-01-2024 2359");
        assertInstanceOf(AddEventTaskCommand.class, command);
    }

    /**
     * Tests interpreting unsupported commands.
     */
    @Test
    public void interpretUserInput_invalid_unsupportedCommandReturned() {
        Command command = UserInputInterpreter.getInstance().interpretUserInput("Invalid.");
        assertInstanceOf(UnsupportedCommand.class, command);
    }

    /**
     * Tests interpreting commands with no task name. Expects an exception
     */
    @Test
    public void interpretUserInput_exceptions_throwException() {
        try {
            Command command = UserInputInterpreter.getInstance().interpretUserInput("todo");
        } catch (OsirisException e) {
            assertEquals("Task name not provided. Please Reenter.", e.getMessage());
        }
        try {
            Command command = UserInputInterpreter.getInstance().interpretUserInput("deadline");
        } catch (OsirisException e) {
            assertEquals("Invalid input format. Please Reenter. "
                    + "Ensure '/by' is specified for a Deadline Task. "
                    + "E.g. deadline Do Homework /by dd-MM-yyyy .", e.getMessage());
        }
        try {
            Command command = UserInputInterpreter.getInstance().interpretUserInput("event");
        } catch (OsirisException e) {
            assertEquals("Invalid input format. Please Reenter. "
                    + "Ensure '/from' & '/to' is specified for a Event Task. "
                    + "E.g. event School Meeting /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm. "
                    + "Please Reenter.", e.getMessage());
        }
    }
}
