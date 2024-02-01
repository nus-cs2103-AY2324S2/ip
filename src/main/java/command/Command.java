package command;

import data.TaskList;
import data.exception.CoDriverException;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException;

    public boolean isExit() {
        return false;
    }
}