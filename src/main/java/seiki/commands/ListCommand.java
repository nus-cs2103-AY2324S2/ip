package seiki.commands;

import seiki.data.TaskList;
import seiki.storage.Storage;
import seiki.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
