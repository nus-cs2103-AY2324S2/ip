package command;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
