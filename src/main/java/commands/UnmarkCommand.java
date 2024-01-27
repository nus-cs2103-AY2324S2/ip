package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand implements Command {
    private String info;

    public UnmarkCommand(String info) {
        this.info = info;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.unmark(info);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
