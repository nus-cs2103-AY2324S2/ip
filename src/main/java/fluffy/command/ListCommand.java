package fluffy.command;

import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
