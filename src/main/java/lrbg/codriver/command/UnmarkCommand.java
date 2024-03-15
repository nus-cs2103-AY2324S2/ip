package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to unmark. */
    private final int index;

    /**
     * Creates a new UnmarkCommand with the given index.
     * @param index The index of the task to unmark, as represented in the UI.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        tasks.unmarkTask(index);
        return ui.showUnmarkTask(tasks.getTask(index - 1));
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same index, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof UnmarkCommand) {
            UnmarkCommand other = (UnmarkCommand) obj;
            return other.index == this.index;
        } else {
            return false;
        }
    }
}
