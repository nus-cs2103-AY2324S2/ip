package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        ui.showExitMessage();
    }
}
