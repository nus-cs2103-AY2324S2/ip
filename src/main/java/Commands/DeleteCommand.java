package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import TasksStorage.Storage;
import UI.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage, String message) throws LeluException {
        int i = Integer.parseInt(message.split(" ")[1]) - 1;
        Task t = tasks.get(i);
        tasks.remove(i);
        ui.deleteMessage(t, tasks.size());
    }
}
