package command;

import exception.BluException;
import storage.Storage;
import task.TaskList;
import ui.UI;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        ui.showExitMessage();
    }
}
