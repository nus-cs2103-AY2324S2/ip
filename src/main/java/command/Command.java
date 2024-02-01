package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;



public abstract class Command {

    public boolean isExit;

    public Command() {
        isExit = false;
    }

    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public void confirmExit() {
        this.isExit = true;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

}
