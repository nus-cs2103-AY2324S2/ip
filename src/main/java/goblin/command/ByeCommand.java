package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();;
        ui.closeScanner();
    }

    public boolean isWorking() {
        return false;
    }
}
