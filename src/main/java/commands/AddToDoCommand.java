package commands;

import exceptions.LeluException;
import tasks.Task;
import tasks.ToDo;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

public class AddToDoCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = ToDo.ToDoOf(message);
        tasks.addTask(t);
    }
}
