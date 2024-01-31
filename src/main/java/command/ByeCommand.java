package command;

import run.Storage;
import run.TaskList;
import run.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) {
        u.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}