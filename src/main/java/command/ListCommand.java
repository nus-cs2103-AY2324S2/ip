package command;

import utilities.Storage;
import task.TaskList;
import utilities.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showList(taskList);
    }
}
