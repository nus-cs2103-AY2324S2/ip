package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;
import duke.utils.Parser;

/**
 * Class to run the Unmark Command
 *
 * @author KohGuanZeh
 */
public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    /**
     * Creates a Command that runs to unmark a task as not done at a specified index.
     *
     * @param input Input of task to mark.
     * @throws CommandException Exception when input is not given in integer format.
     */
    public UnmarkCommand(String input) throws CommandException {
        try {
            this.indexToUnmark = Parser.parseInteger(input);
        } catch (NumberFormatException e) {
            throw new CommandException("Error. Unmark expects the index of task to be unmarked.");
        }
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        String message = taskList.unmarkTask(this.indexToUnmark);
        storage.save(taskList.toDataString());
        return message;
    }
}
