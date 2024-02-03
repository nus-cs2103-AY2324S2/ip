package Duke.Command;

import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        this.isActive = false;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.farewell();
    }
}
