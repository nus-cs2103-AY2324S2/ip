package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;

public class ByeCommand extends Commands {
    public ByeCommand() {
        super();
    }

    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) {
        ui.displayExit();
        return true;
    }
}
