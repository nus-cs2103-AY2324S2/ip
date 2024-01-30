package Command;

import Utilities.Storage;
import Task.TaskList;
import Utilities.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showList(taskList);
    }
}
