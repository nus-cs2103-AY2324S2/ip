package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class NoreplyCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
