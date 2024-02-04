package command;

import helpers.TaskList;
import helpers.Ui;
import task.Task;

public class DeleteCommand extends Command {
    private final int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task deletedTask = taskList.deleteTask(i - 1);
        ui.displayDeletedTask(deletedTask, taskList.getLength());
    }
}
