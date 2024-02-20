package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.task.ToDo;
import duke.utils.FormatException;
import duke.utils.Parser;

/**
 * Class to run the ToDo Command.
 *
 * @author KohGuanZeh
 */
public class ToDoCommand extends Command {
    private final ToDo toDo;

    /**
     * Creates a Command that runs to add a ToDo task.
     *
     * @param input Input of ToDo information.
     * @throws CommandException Exception to inform user of formatting error in input.
     */
    public ToDoCommand(String input) throws CommandException {
        try {
            this.toDo = Parser.parseToDoInput(input);
        } catch (FormatException e) {
            throw new CommandException("Error. Unable to create ToDo task.\nFormat: " + ToDo.INPUT_TODO_FORMAT);
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.addTask(this.toDo);
        storage.save(taskList.toDataString());
        return message;
    }
}
