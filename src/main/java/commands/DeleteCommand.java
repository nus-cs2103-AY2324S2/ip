package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand implements Command {
    private String info;

    public DeleteCommand(String info) {
        this.info = info;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(info);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
