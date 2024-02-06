package commands;

import exceptions.LeluException;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        ui.exit();
        storage.save(tasks);
    }
}
