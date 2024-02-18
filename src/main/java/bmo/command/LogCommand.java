package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

/**
 * Represents a log command to be executed.
 */
public class LogCommand extends Command {

    /**
     * Executes the log command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLog(tasks);
    }
}
