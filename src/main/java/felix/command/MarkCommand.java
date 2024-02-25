package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to mark task as done
 */
public class MarkCommand extends Command {
    private final int indexToMark;
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Marks task at given index as done.
     * Throws an exception if given index is out of range.
     * Saves updated task list to storage file.
     * Returns String representation of marked task.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.indexToMark);
            task.markAsDone();
            assert tasks.getTask(this.indexToMark).getStatus() : "task was not marked as done successfully";
            storage.writeToFile(tasks);
            return ui.getMarkMessage(task);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
