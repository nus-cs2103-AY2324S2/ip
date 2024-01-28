package saopig.command;

import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
