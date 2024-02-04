package Commands;

import Misc.StorageManager;
import Misc.Ui;
import Irwyn.Tasks.TaskList;

public class EndCommand extends Command {
    EndCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        ui.end();
    }
}