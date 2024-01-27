package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class DeadlineCommand implements Command {
    private String info;

    public DeadlineCommand(String info) {
        this.info = info;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deadline(info);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
