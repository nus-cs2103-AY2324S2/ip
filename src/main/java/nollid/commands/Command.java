package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.NollidException;


/**
 * Command class is an abstract class representing an executable command.
 * It defines the execute method that subclasses must implement to perform specific command logic.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException;
}
