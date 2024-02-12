package tyler.command;

import tyler.ui.Ui;
import tyler.storage.Storage;
import tyler.task.TaskList;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}