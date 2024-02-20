package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

public abstract class Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }
    public boolean isExit() {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }

}
