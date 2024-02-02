package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
public abstract class Command {
    protected boolean isDone;
    public Command(boolean isDone) {
        this.isDone = isDone;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return this.isDone;
    }

}
