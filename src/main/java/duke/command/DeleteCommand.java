package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.Parser;

/**
 * Class to run Delete Command.
 *
 * @author KohGuanZeh
 */
public class DeleteCommand extends Command {
    private final int indexToDelete;

    /**
     * Creates a Command that runs to delete a task at a specified index.
     *
     * @param input Input of task to delete.
     * @throws CommandException Exception when input is not given in integer format.
     */
    public DeleteCommand(String input) throws CommandException {
        try {
            this.indexToDelete = Parser.parseInteger(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Delete expects the index of task to be deleted.");
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.deleteTask(this.indexToDelete);
        storage.save(taskList.toDataString());
        return message;
    }
}
