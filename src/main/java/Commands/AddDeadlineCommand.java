package Commands;

import Exceptions.LeluException;
import Tasks.Deadline;
import Tasks.Task;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import UI.Ui;

import java.util.ArrayList;

public class AddDeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = Deadline.DeadlineOf(message);
        tasks.addTask(t);
    }
}
