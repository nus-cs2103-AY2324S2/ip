package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ErrorCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    String error;
    public ErrorCommand(Ui ui, TaskList taskList, Storage storage, String error) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.error = error;
    }
    @Override
    public void execute() throws DukeException {
        this.ui.printErrorMessage(this.error);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
