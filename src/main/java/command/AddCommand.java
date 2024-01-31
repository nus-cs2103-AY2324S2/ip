package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {

    protected Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAdd(this.task, tasks.size());
    }
}
