package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
