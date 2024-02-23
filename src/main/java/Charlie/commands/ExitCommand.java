package Charlie.commands;

import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        isExit = true;
    }
}
