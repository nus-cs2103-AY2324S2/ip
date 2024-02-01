package command;

import data.TaskList;
import ui.Ui;
import storage.Storage;

public class ByeCommand extends Command {
    public ByeCommand() {
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
