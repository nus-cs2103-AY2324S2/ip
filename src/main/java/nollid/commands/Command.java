package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.NollidException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException;
}
