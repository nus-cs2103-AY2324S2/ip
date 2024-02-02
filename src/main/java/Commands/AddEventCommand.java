package Commands;

import Exceptions.LeluException;
import Tasks.Event;
import Tasks.Task;
import TasksStorage.Storage;
import UI.Ui;

import java.util.ArrayList;

public class AddEventCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = Event.EventOf(message);
        tasks.add(t);
        ui.addMessage(t, tasks.size());
    }
}
