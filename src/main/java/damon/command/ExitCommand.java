package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents ExitCommand object which is corresponding to user's input for exiting.
 */
public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Executes ExitCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

