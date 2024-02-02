package zack.commands;

import zack.ZackException;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

import java.io.IOException;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException;

    public boolean isExit() {
        return isExit;
    }
}
