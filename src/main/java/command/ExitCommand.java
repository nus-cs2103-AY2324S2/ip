package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    /**
     * Saves the current TaskList and closes program.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storeToLocal(tasks);
        ui.goodbyePrinter();
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return true;
    }
}
