package fluffy.command;

import fluffy.FluffyException;
import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for MarkCommand.
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     * @throws FluffyException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FluffyException {
        tasks.getTask(index).markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n" + tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
