package shodan.command.impl;

import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

public class ByeCommand extends Command {
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) {
        ui.showExitMsg();
        return true;
    }
}
