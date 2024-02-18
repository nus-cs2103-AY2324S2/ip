package doummi.command;

import doummi.Storage;
import doummi.task.TaskList;
import doummi.Ui;

public abstract class Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }
    public boolean isExit() {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }

}
