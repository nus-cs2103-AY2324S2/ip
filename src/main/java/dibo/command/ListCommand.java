package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.command.Command;
import dibo.exception.DiboException;

public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showList(taskList.getDisplayFormat());
    }
}
