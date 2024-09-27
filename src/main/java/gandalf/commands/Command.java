package gandalf.commands;

import gandalf.GandalfException;
import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

/**
 * Command is an abstract class and any classes that inherits this must implement the execute() method.
 * Every inheritor will have the implemented behaviour of setExit() and toString() unless it is overridden.
 */
public abstract class Command {
    protected String commandName;
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;
    protected String[] otherInputs;

    public Command(String commandName, TaskList tasks, Storage storage, Ui ui, String... otherInputs) {
        this.commandName = commandName;
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.otherInputs = otherInputs;
    }

    public abstract String execute() throws GandalfException;

    public boolean setExit() {
        return false;
    }
    @Override
    public String toString() {
        return commandName + " command executed";
    }
}
