package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to delete a task
 */
public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Deletes task at given index from task list.
     * Throws an exception if given index is not within range.
     * Saves updated task list to storage file.
     * Returns String representation of display message.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.deleteIndex);
            tasks.deleteTask(deleteIndex);
            storage.writeToFile(tasks);
            return ui.getDeleteTaskMessage(task, tasks.getCount());
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
