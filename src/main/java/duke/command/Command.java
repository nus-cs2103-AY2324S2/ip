package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage s);

    public abstract boolean isExit();
}

