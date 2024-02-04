package lrbg.codriver.command;

import lrbg.codriver.data.TaskList;
import lrbg.codriver.ui.Ui;
import lrbg.codriver.storage.Storage;

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

    public boolean testEquals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
