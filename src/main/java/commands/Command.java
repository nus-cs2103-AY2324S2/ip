package commands;

import exceptions.FileError;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public Boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FileError;
}
