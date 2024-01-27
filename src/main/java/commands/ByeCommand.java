package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No action needed for ByeCommand
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
