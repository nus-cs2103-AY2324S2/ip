package bmo.command;

import bmo.ui.Ui;
import bmo.util.Storage;
import bmo.util.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printLog(tasks);
    }
}
