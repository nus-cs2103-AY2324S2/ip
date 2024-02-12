package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.list());
    }
}
