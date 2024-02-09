package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Represents a command which can be executed
 */
public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage s);

    public abstract boolean isExit();
}

