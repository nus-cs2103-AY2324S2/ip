package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SamException;
    public boolean isExit() {
        return false;
    }
}
