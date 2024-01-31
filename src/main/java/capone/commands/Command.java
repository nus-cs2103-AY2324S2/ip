package capone.commands;

import capone.exceptions.CaponeException;
import capone.TaskList;
import capone.TaskStorage;
import capone.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, TaskStorage storage) throws CaponeException;

}
