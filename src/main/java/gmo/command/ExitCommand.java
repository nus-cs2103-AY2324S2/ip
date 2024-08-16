package gmo.command;

import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

/**
 * Represents an exit command to be executed.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        return ui.salute();
    }

    public boolean isExit() {
        return true;
    }
}
