package chad.command;

import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

/**
 * Represents a command which can be executed
 */
public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage s);

    public abstract boolean isExit();
}

