package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BobException;

    public boolean checkExit() {
        return false;
    }
}
