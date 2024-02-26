package dude.Commands;

import dude.Exceptions.DudeException;
import dude.Exceptions.InvalidDescriptionException;
import dude.Exceptions.TaskListFullException;
import dude.tasks.TaskList;
import dude.tasks.Todo;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParsingListCommand() {
        TaskList taskList = new TaskList();
        String input = "list";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof ListCommand);
    }

    @Test
    public void testParsingByeCommand() {
        TaskList taskList = new TaskList();
        String input = "bye";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof ByeCommand);
    }

    @Test
    public void testParsingDeleteCommand() throws DudeException {

        //add a task to the tasklist
        TaskList taskList = new TaskList();
        taskList.add_task(Todo.from("todo test"));

        String input = "delete 1";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof DeleteCommand);
    }

    @Test
    public void testParsingTodoCommand() {
        TaskList taskList = new TaskList();
        String input = "todo test";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof TodoCommand);
    }

    @Test
    public void testParsingDeadlineCommand() {
        TaskList taskList = new TaskList();
        String input = "deadline test /by 2020-12-12";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof DeadlineCommand);
    }

    @Test
    public void testParsingEventCommand() {
        TaskList taskList = new TaskList();
        String input = "event test /from 2/12/2020 1800 /to 2/12/2020 1900";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof EventCommand);
    }

    @Test
    public void testParsingMarkCommand() throws InvalidDescriptionException, TaskListFullException {
        TaskList taskList = new TaskList();
        taskList.add_task(Todo.from("todo test"));
        String input = "mark 1";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof MarkCommand);
    }

    @Test
    public void testParsingUnmarkCommand() throws InvalidDescriptionException, TaskListFullException {
        TaskList taskList = new TaskList();
        taskList.add_task(Todo.from("todo test"));
        String input = "unmark 1";

        Command command = Parser.parse(input, taskList);
        assert (command instanceof UnmarkCommand);
    }
}
