package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

public abstract class Command {

    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DiboException;
    public boolean isBye() {
        return false;
    }

}
