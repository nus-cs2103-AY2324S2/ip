package bytebuddy.commands;

import bytebuddy.exceptions.DukeException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

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
