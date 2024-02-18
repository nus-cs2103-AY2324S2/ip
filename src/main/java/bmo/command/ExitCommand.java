package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.salute();
        storage.saveData(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
