package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

/**
 * Represents an exit command to be executed.
 */
public class ExitCommand extends Command{

    /**
     * Executes the exit command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.salute();
        storage.saveData(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
