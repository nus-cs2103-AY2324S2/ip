package jade.commands;

import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;
import jade.exception.JadeException;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui,Storage storage) throws JadeException;
    public abstract boolean isExit();
}
