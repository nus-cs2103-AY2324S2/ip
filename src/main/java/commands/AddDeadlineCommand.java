package commands;

import exceptions.LeluException;
import tasks.Deadline;
import tasks.Task;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class AddDeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = Deadline.DeadlineOf(message);
        tasks.addTask(t);
    }
}
