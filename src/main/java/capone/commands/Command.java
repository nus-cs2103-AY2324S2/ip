package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException;

}
