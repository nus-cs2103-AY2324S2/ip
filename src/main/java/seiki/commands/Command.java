package seiki.commands;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException;
    public abstract boolean isExit();
}
