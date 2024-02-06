package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import Tasks.ToDo;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import Ui.Ui;

public class AddToDoCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = ToDo.ToDoOf(message);
        tasks.addTask(t);
    }
}
