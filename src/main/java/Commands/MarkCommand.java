package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import TasksStorage.Storage;
import UI.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage, String message) throws LeluException {
        int i = Integer.parseInt(message.split(" ")[1]) - 1;
        Task t = tasks.get(i);
        t.markTask();
        ui.markMessage(t);
    }

}
