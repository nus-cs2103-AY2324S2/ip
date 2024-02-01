package Thames.command;

import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;
import Thames.ThamesException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException;

    public abstract boolean isExit();
}
