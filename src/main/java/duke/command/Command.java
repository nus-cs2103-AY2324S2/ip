package duke.command;


import duke.common.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;


public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
