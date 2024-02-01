package jade.commands;

import jade.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, jade.ui.Ui ui, jade.storage.Storage storage);
    public abstract boolean isExit();
}
