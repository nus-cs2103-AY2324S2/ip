package Commands;

import Irwyn.Tasks.TaskList;
import Misc.Ui;
import Misc.StorageManager;

public abstract class Command {
    boolean isExit;
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, StorageManager storageManager);

    public boolean isExit() {
        return this.isExit;
    }
}