package Commands;

import Exceptions.LeluException;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import Ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        ui.exit();
        storage.save(tasks);
    }
}
