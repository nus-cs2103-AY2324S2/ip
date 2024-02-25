package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to unmark a task as done
 */
public class UnmarkCommand extends Command {
    private final int indexToUnmark;
    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Unmarks task at given index as done.
     * Throws an exception if given index is out of range.
     * Saves updated task list to storage file.
     * Returns String representation of unmarked file.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.indexToUnmark);
            task.unmarkDone();
            assert !tasks.getTask(this.indexToUnmark).getStatus() : "task was not unmarked successfully";
            storage.writeToFile(tasks);
            return ui.getUnmarkMessage(task);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
