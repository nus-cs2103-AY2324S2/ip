package commands;

import exceptions.FileError;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        storage.write(tasks.getTaskList());
        ui.showBye();
    }



    @Override
    public Boolean isExit() {
        return true;
    }
}
