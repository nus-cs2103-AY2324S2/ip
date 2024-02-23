package charlie.commands;

import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        isExit = true;
    }
}
