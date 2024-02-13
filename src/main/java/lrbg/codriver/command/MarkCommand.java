package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /** The index of the task to mark. */
    private final int index;

    /**
     * Creates a new MarkCommand with the given index.
     * @param index The index of the task to mark, as represented in the UI.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        tasks.markTask(index);
        return ui.showMarkTask(tasks.getTask(index - 1));
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same index, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof MarkCommand) {
            MarkCommand other = (MarkCommand) obj;
            return other.index == this.index;
        } else {
            return false;
        }
    }
}
