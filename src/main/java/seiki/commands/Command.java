package seiki.commands;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the abstract class that all Command classes inherits from.
 */
public abstract class Command {
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException;
    public abstract boolean isExit();
}
