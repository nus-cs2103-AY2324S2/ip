package capone.commands;

import capone.exceptions.CaponeException;
import capone.TaskList;
import capone.Storage;
import capone.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException;

}
