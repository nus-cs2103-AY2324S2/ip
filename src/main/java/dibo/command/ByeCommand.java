package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

public class ByeCommand extends Command {
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.sayBye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
