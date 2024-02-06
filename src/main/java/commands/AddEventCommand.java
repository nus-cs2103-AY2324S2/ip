package commands;

import exceptions.LeluException;
import tasks.Event;
import tasks.Task;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class AddEventCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = Event.EventOf(message);
        tasks.addTask(t);
    }
}
