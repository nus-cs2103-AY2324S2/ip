package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
