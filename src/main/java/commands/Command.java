package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Generic Command class that executes instructions based on user input
 */
public abstract class Command {
    public Boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws FileError;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && this.getClass() == obj.getClass();
    }

    public abstract String showUsage();
}
