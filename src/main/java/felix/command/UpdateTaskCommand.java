package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to update task at particular index
 */
public class UpdateTaskCommand extends Command {
    private final int indexToUpdate;
    private final String paramString;

    public UpdateTaskCommand(int indexToUpdate, String paramString) {
        this.indexToUpdate = indexToUpdate;
        this.paramString = paramString;
    }

    /**
     * Updates task at given index.
     * Throws an exception if given index is out of range.
     * Saves updated task list to storage file.
     * Returns String representation of marked task.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task oldTask = tasks.getTask(indexToUpdate);
            Task newTask = oldTask.updateTask(paramString);
            tasks.setTask(indexToUpdate, newTask);
            storage.writeToFile(tasks);
            return ui.getUpdateMessage(oldTask, newTask);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }

}
