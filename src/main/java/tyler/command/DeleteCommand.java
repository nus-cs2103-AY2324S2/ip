package tyler.command;

import tyler.ui.Ui;
import tyler.storage.Storage;
import tyler.task.Task;
import tyler.task.TaskList;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(this.index - 1);
        tasks.deleteTask(this.index - 1);
        int num = tasks.getNumOfTasks();
        ui.showTaskDeleted(deletedTask, num);
    }
}
