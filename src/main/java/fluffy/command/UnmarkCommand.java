package fluffy.command;

import fluffy.FluffyException;
import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnmarkCommand.
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     * @throws FluffyException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FluffyException {
        tasks.getTask(index).markAsNotDone();
        ui.showMessage("Nice! I've marked this task as undone:\n" + tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
