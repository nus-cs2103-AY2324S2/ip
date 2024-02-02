package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import TasksStorage.Storage;
import UI.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage, String message) throws LeluException {
        ui.exit();
        storage.save(tasks);
    }
}
