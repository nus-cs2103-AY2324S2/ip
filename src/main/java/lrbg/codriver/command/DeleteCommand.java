package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to delete. */
    private final int index;

    /**
     * Creates a new DeleteCommand with the given index.
     * @param index The index of the task to delete, as represented in the UI.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        return ui.showDeleteTask(task, tasks.getSize());
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same index, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return other.index == this.index;
        } else {
            return false;
        }
    }
}
