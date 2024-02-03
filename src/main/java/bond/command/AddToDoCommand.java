package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.Task;
import bond.task.TaskList;
import bond.task.ToDoTask;

public class AddToDoCommand extends AddCommand {

    public AddToDoCommand(String taskName) {
        super("todo", taskName);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new ToDoTask(super.taskName);
        tasks.addTask(newTask);
        ui.taskAdded(newTask, tasks);
        storage.storeTask(newTask, tasks);
    }

}
