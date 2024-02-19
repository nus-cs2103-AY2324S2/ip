package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

